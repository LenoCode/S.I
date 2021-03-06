package socket_installer.SI_parts.exception.client.general;


import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;


public class ClientTimeoutException extends SocketExceptions {
    //OVO NE TREBA UOPCE, JEDINO MOZDA PRONACI NESTO STO BI SE TU MOGLO PROVJERAVATI
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
            System.out.println("DEACTIVED");
            socketModel.deactivateSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }
}
