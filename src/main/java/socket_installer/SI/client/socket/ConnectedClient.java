package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_parts.io_components.IO.processor.IOProcessor;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClient extends ClientSocket {


    public ConnectedClient(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        ClientConfiguration clientConfiguration = (ClientConfiguration) getSocketConfiguration();

        while(clientConfiguration.isSocketOnline()){
            IOProcessor.getIoProcessor().initializeBytesReading(ioHolder);

            if (actions.getBufferChecker().checkStringBuffer(this)){
                System.out.println("Tu sam a disi ti");
                actions.getBytesResponder().sendBytesRecv(ioHolder);
                ioHolder.getStringBuffer().emptyBuffer();
            }
        }

    }
}

