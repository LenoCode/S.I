package socket_installer.SI_parts.exception.client.connection_break_exception;

import socket_installer.SI.client.socket.Client;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;

public class ClientConnectionAbortException extends SocketExceptions {
    @Override
    public void handleException(SocketModel socketModel) {
        Client client = (Client) socketModel;
        try {
            client.reconnectSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }

}
