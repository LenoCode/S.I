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
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;
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

    public void sendNotification(ClientSocket clientSocket,String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions{
        System.out.println("sending notification");
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

        System.out.println("reading client messages from stream ----------->");
        setInputStreamToBlock(clientSocket);
        do{
            readProcessor.readDataFromOpenStream(clientSocket,readStatusProcessorModel);
            checkStatusFromReadStatusProcessor(readStatusProcessorModel,notificationerActions,stringBuffer,clientSocket);
        }while(readStatusProcessorModel.checkIfStreamOpen());

        setInputStreamToUnblock(clientSocket);
        checkStreamClosingStatus(clientSocket,readStatusProcessorModel);
        System.out.println("reading client message is done , cleaning stringBuffer  "+stringBuffer.getString());
        stringBuffer.emptyBuffer();
    }

    public int readingBytesFromStream(ClientSocket clientSocket,byte[] bytes) throws IOException, SocketExceptions{
        return readProcessor.readBytesFromStream(clientSocket, bytes);
    }
    //TREBA NAPRAVITI IF STATEMENT AKO TU DODE REQUEST ZA OPEN STREAM,DA SE OBAVIJESTI CLIENTA DA JE STREAM OPEN
    protected void checkStatusFromReadStatusProcessor(ReadStatusProcessorModel readStatusProcessorModel,
                                                      NotificationerActions notificationerActions,
                                                      StringBuffer stringBuffer,
                                                      ClientSocket clientSocket) throws IOException, SocketExceptions {

        ProcessorEnums status = readStatusProcessorModel.checkReadStatus();
        if (status == ProcessorEnums.DATA_LINE_COMPLETE || status == ProcessorEnums.DATA_COMPLETE){
            System.out.println("DATA LINE COMPLETE");
            notifyClass(clientSocket,readStatusProcessorModel,notificationerActions,stringBuffer);

        } else if (readStatusProcessorModel.checkReadStatus() == ProcessorEnums.STREAM_CONNECTION_LOST){
            notificationerActions.exceptionHandler(readStatusProcessorModel);
        }
    }


    private void notifyClass(ClientSocket clientSocket,
                             ReadStatusProcessorModel readStatusProcessorModel,
                             NotificationerActions notificationerActions,
                             StringBuffer stringBuffer) throws IOException, SocketExceptions {
        Iterator<String> iterator = bufferProcessor.parseNotifications(stringBuffer);
        System.out.println("Notify class      ,buffer looks like this ->  "+stringBuffer.getString());
        stringBuffer.emptyBuffer();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("CHECKING NOTIFICATION ---------->  "+next);
            if (isClosingNotification(next)){
                if (isItIsReadyToClose(clientSocket,iterator.hasNext())){
                    System.out.println("NOTIFICATION IS FOR CLOSING");
                    checkWhoInitiateClosing(readStatusProcessorModel,next);
                }
            }else{
                System.out.println("NOTIFICATION IS NOT FOR CLOSING");
                notificationerActions.notifyClass( bufferProcessor.extractNotification(next) );
            }
        }
    }

    private boolean isClosingNotification(String notification){
        return notification.equals(TechnicalProtocol.SOCKET_STREAM_CLOSING.identProtocol()) || notification.equals(TechnicalProtocol.SOCKET_STREAM_CLOSED.identProtocol());
    }
    private boolean isItIsReadyToClose(ClientSocket clientSocket,boolean hasNext){
        if (hasNext){
            System.out.println("Has next");
            return false;
        }else if (readProcessor.isThereDataInStream(clientSocket)){
            System.out.println("It got some data in stream");
            return false;
        }
        System.out.println("It is ready for closing");
        return true;
    }

    private void checkWhoInitiateClosing(ReadStatusProcessorModel readStatusProcessorModel,String notification){
        if (notification.equals(TechnicalProtocol.SOCKET_STREAM_CLOSING.identProtocol())){
            readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSING);
        }else{
            readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
        }
    }

    private void checkStreamClosingStatus(ClientSocket clientSocket,ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        if (readStatusProcessorModel.getStreamClosingStatus() == ProcessorEnums.STREAM_CLOSING){
            sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol().getBytes());
            readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
        }
    }

    private void setInputStreamToBlock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(0);
    }
    private void setInputStreamToUnblock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(clientSocket.getSocketConfiguration().getTimeout());
    }

}
