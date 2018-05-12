package socket_installer.SI_parts.exception.client.connection_break_exception;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionAbortException extends SocketExceptions {
    @Override
    public void handleException(SocketModel socketModel) {
        Client client = (Client) socketModel;
        ClientConfiguration configuration = (ClientConfiguration) client.getSocketConfiguration();
        String ipAddress = configuration.getIpAddress();
        int port = configuration.getPort();

        createSocket(client,ipAddress,port);
    }

    private void createSocket(Client client,String ipAddress,int port){
        System.out.println("New socket created");
        try {
            Socket socket = new Socket(ipAddress,port);
            client.replaceSocket(socket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }
}
