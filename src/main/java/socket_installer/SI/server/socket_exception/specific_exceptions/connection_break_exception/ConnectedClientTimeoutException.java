package socket_installer.SI.server.socket_exception.specific_exceptions.connection_break_exception;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;


public class ConnectedClientTimeoutException extends SocketExceptions {

    @Override
    public void handleException(SocketModel socketModel) {
        ClientSocket clientSocket = (ClientSocket) socketModel;
        streamPaused((ClientConfiguration) clientSocket.getSocketConfiguration());
        closeSocket(socketModel);
    }

    private void streamPaused(ClientConfiguration clientConfiguration){
        clientConfiguration.setStreamPaused(true);
    }


    private void closeSocket (SocketModel socketModel){
        try {
            socketModel.deactivateSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }
}
