package socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor;


import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.buffer_processor.BufferProcessor;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.IO.communication_processor.read_processor.ReadProcessor;
import socket_installer.SI_parts.IO.communication_processor.send_processor.SendProcessor;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.protocol.enum_protocols.data_protocol.DataProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public abstract class MainProcessor {

    protected final SendProcessor sendProcessor;
    protected final ReadProcessor readProcessor;
    protected final BufferProcessor bufferProcessor;

    public MainProcessor(){
        sendProcessor = new SendProcessor();
        readProcessor = new ReadProcessor();
        bufferProcessor = new BufferProcessor();
    }

    public void notifyClass(NotificationerActions notificationerActions, StringBuffer stringBuffer) throws IOException, SocketExceptions {
        Iterator<String> iterator = bufferProcessor.parseDataRecieved(stringBuffer);

        stringBuffer.emptyBuffer();
        while(iterator.hasNext()) {
            String next = iterator.next();
            notificationerActions.notifyClass( bufferProcessor.extractNotification(next) );
        }
    }
    public void sendNotification(ClientSocket clientSocket,String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions{
        notification = DataProtocol.sendMessageFormat(classIdent,methodIdent,notification);
        sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),notification.getBytes());
    }
    public void sendData(ClientSocket clientSocket,byte[] bytes) throws IOException,SocketExceptions{
        sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),bytes);
    }

    public void readingDataFromStream(ClientSocket clientSocket) throws IOException, SocketExceptions{
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        NotificationerActions notificationerActions = clientSocket.getNotificationer();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();

        setInputStreamToBlock(clientSocket);
        do{
            readProcessor.readDataFromOpenStream(clientSocket,readStatusProcessorModel);
            checkStatusFromReadStatusProcessor(readStatusProcessorModel,notificationerActions,stringBuffer);
        }while(readStatusProcessorModel.checkIfStreamOpen());

        setInputStreamToUnblock(clientSocket);
        checkStreamClosingStatus(clientSocket,readStatusProcessorModel);
        stringBuffer.emptyBuffer();
    }

    public int readingBytesFromStream(ClientSocket clientSocket,byte[] bytes) throws IOException, SocketExceptions{
        return readProcessor.readBytesFromStream(clientSocket, bytes);
    }

    protected void checkStatusFromReadStatusProcessor(ReadStatusProcessorModel readStatusProcessorModel, NotificationerActions notificationerActions, StringBuffer stringBuffer) throws IOException, SocketExceptions {

        if (readStatusProcessorModel.checkReadStatus() == ProcessorEnums.DATA_LINE_COMPLETE){
            notifyClass(notificationerActions,stringBuffer);

        }else if (readStatusProcessorModel.checkReadStatus() == ProcessorEnums.DATA_COMPLETE){
            String savedString = stringBuffer.getString();

            if (!areThereDataStillInBuffer(notificationerActions,stringBuffer)){
                if (savedString.equals(TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol())){
                    readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSING);
                }else{
                    readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
                }
            }

        } else if (readStatusProcessorModel.checkReadStatus() == ProcessorEnums.STREAM_CONNECTION_LOST){
            notificationerActions.exceptionHandler(readStatusProcessorModel);
        }
    }

    protected boolean areThereDataStillInBuffer(NotificationerActions notificationerActions, StringBuffer stringBuffer) throws IOException, SocketExceptions {
        bufferProcessor.removeSocketStreamClosedLine(stringBuffer);
        if (stringBuffer.getString().length() > 0){
            notifyClass(notificationerActions,stringBuffer);
            return true;
        }else{
            return false;
        }
    }

    protected void setInputStreamToBlock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(0);
    }
    protected void setInputStreamToUnblock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(clientSocket.getSocketConfiguration().getTimeout());
    }

    private void checkStreamClosingStatus(ClientSocket clientSocket,ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        if (readStatusProcessorModel.getStreamClosingStatus() == ProcessorEnums.STREAM_CLOSING){
            System.out.println("saljem closed");
            sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol().getBytes());
            readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
        }
    }

}
