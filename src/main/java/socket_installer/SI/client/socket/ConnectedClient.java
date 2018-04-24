package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;
import socket_installer.SI_parts.io_components.parts_for_bytes.BytesReader;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClient extends ClientSocket {


    public ConnectedClient(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        while(clientConfiguration.isSocketOnline()){
            BytesReader.getBytesReader().readBytes(this);
            String message =ioHolder.getStringBuffer().emptyBuffer();
            System.out.println(message);
        }
    }
}
