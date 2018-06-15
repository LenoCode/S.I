package socket_installer.SI_parts.IO.communication_processor_test_2.main_processors;

import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientMainProcessor extends MainProcessor {


    public void openStreamSocket(ClientSocket clientSocket) throws IOException, SocketExceptions {
        String dataToSend = TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();

        notifyServerAboutOpendStream(clientSocket,readStatusProcessorModel,dataToSend);

        if (bufferProcessor.checkProtocolInBuffer(stringBuffer,TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol())){
            clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorsEnums.STREAM_OPEN);
        }else{
            throw  new ClientClosedException();
        }
    }
    public void readingDataFromStream(ClientSocket clientSocket) throws IOException, SocketExceptions{
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        setInputStreamToBlock(clientSocket);

        do{
            readProcessor.readDataFromOpenStream(clientSocket,readStatusProcessorModel);

        }while(readStatusProcessorModel.checkIfStreamOpen());

        setInputStreamToUnblock(clientSocket);
    }

    public void sendNotification(ClientSocket clientSocket,String notification) throws IOException, SocketExceptions{
        sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),notification.getBytes());
    }


    private void notifyServerAboutOpendStream(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel, String dataToSend) throws IOException, SocketExceptions {
        readStatusProcessorModel.setCheckReadStatus(ProcessorsEnums.INITILIAZED);
        do {
            ProcessorsEnums readStatus = readStatusProcessorModel.checkReadStatus();

            if (readStatus == ProcessorsEnums.INITILIAZED || readStatus == ProcessorsEnums.FIRST_TRY){
                sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),dataToSend.getBytes());
            }
            readProcessor.readStreamStatus(clientSocket,readStatusProcessorModel);
        }while(readStatusProcessorModel.checkStreamStatus(clientSocket));
    }


    private void setInputStreamToBlock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(0);
    }
    private void setInputStreamToUnblock(ClientSocket clientSocket) throws SocketException {
        ((Socket)clientSocket.getSocket()).setSoTimeout(clientSocket.getSocketConfiguration().getTimeout());
    }

}
