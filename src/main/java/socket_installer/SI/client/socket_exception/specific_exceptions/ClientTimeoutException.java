package socket_installer.SI.client.socket_exception.specific_exceptions;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;


public class ClientTimeoutException extends SocketExceptions {

    @Override
    public void handleException(SocketModel socketModel) {
        ClientSocket clientSocket = (ClientSocket) socketModel;
        clientSocket.getClientConfiguration().setStreamPaused(true);
    }
}
