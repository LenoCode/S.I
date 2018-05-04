package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.exception.default_exception.NoResponseRequestException;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.io_components.IO.processor.IOProcessor;
import socket_installer.SI_parts.io_components.IO.wrapper.client.ClientInputStreamWrapper;
import socket_installer.SI_parts.io_components.IO.wrapper.client.ClientOutputStreamWrapper;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
        boolean waitingForResponse = true;

        while (waitingForResponse) {
            try {
                System.out.println("Waiting response");
                IOProcessor.getIoProcessor().initializeBytesReading(ioHolder);

                if (actions.getBufferChecker().checkStringBuffer(this)) {
                    waitingForResponse = false;
                }
            } catch (SocketExceptions socketExceptions) {
                if (socketExceptions instanceof NoResponseRequestException){
                    waitingForResponse = false;
                }else{
                    throw socketExceptions;
                }
            } catch (IOException ioException) {
                System.out.println("IoException se pojavio");
            }
        }
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
    }

    @Override
    public void replaceSocket(Socket socket) throws IOException, SocketExceptions {
        this.socket = socket;
        ioHolder.getStringBuffer().emptyBuffer();
        setupStream(socket);
        ((ClientConfiguration)socketConfiguration).setStreamPaused(false);
        socketConfiguration.setSocketOnlineStatus(true);
    }
    private void setupStream(Socket socket) throws IOException, SocketExceptions{
        ioHolder.setInputStream(new ClientInputStreamWrapper( new BufferedInputStream(socket.getInputStream()) ));
        ioHolder.setOutputStream(new ClientOutputStreamWrapper( new BufferedOutputStream(socket.getOutputStream()) ));
    }
}
