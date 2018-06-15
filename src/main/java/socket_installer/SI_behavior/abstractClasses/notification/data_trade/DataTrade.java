package socket_installer.SI_behavior.abstractClasses.notification.data_trade;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.protocol.enum_protocols.data_protocol.DataProtocol;

import java.io.IOException;

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
    public void send(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions {
        String completeString = DataProtocol.sendMessageFormat(classIdent,methodIdent,data);
        //CommunicationProcessor.getCommunicationProcessor().activateSendProcess(clientSocket,completeString);
    }
}
