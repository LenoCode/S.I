package socket_installer.SI_behavior.interfaces.notification;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.external_context.ExternalContext;
import java.io.IOException;


public interface DataTradeModel {
     void setClientSocket(ClientSocket clientSocket);
     ClientSocket getClientSocket();
     void send(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions;
     void injectExternalContext(ExternalContext externalContext);
     ExternalContext getExternalContext();

}
