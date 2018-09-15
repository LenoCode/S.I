package socket_installer.SI_behavior.abstractClasses.notification.data_trade;


import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.SignalProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.net.SocketException;

public abstract class DataTrade implements DataTradeModel {
    private ClientSocket clientSocket;
    private ExternalContext externalContext;

    @Override
    public void setClientSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void injectExternalContext(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }
    @Override
    public ExternalContext getExternalContext() {
        return externalContext;
    }

    @Override
    public void resetExternalContext() {
        externalContext.clearContextObjects();
        externalContext = null;
    }

    @Override
    public void send(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions {
        try{
            CommunicationProcessor.MainProcessor().sendNotification(clientSocket,classIdent,methodIdent,data);
        }catch (IOException|SocketExceptions exception){
            if (clientSocket instanceof ConnectedClient){
                System.out.println("Tu sam pao a ti disi ti pao!!!!!!!!!!!!!!!!!!");
                ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
                readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.increaseProccesorCount(readStatusProcessorModel.checkReadStatus()));
            }else{
                System.out.println("Tu sam pao a ti disi ti pao!!!!!!!!!!!!!!!!!!");
            }
        }
    }
    @Override
    public void closeStream() throws IOException, SocketExceptions {
        CommunicationProcessor.MainProcessor().sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol().getBytes());
    }

    @Override
    public void sendSignal(char signal) throws IOException, SocketExceptions {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) signal;
        CommunicationProcessor.MainProcessor().sendData(clientSocket,bytes);
    }

    @Override
    public char waitForSignal() throws IOException, SocketExceptions {
        byte[] signal = new byte[1];
        CommunicationProcessor.MainProcessor().readingBytesFromStream(clientSocket,signal);
        return (char)signal[0];
    }



    @Override
    public int download(byte[] bytes) throws IOException, SocketExceptions {
       return CommunicationProcessor.MainProcessor().readingBytesFromStream(clientSocket,bytes);
    }

    @Override
    public void upload(byte[] bytes) throws IOException, SocketExceptions {
        CommunicationProcessor.MainProcessor().sendData(clientSocket,bytes);
    }

    public void disconnectFromServer(){
        if (clientSocket instanceof ConnectedClient){
            ((ConnectedClient)clientSocket).removeFromSessionTracker();
        }
    }
}
