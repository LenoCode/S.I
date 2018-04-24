package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClient extends ClientSocket {


    public ConnectedClient(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        while(clientConfiguration.isSocketOnline()){
            bytesReader.readBytes(this);
        }
    }
}
