package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.processor.IOProcessor;

import java.io.IOException;
import java.net.Socket;


public class Client extends ClientSocket {


    public Client(Socket clientSocket) {
        super(clientSocket);
    }

    public void sendMessage(String message)throws  IOException, SocketExceptions{
        byte[] bytes = actions.getCommunicationProtocol().implementSentProtocol(message);
        IOProcessor.getIoProcessor().initializeBytesSending(bytes,ioHolder);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        IOProcessor.getIoProcessor().initializeBytesReading(ioHolder);

        if (actions.getBufferChecker().checkStringBuffer(this)){
            ioHolder.getStringBuffer().emptyBuffer();
        }
    }

}
