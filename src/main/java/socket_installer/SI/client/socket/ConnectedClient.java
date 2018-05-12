package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.io_components.IO.processor.IOProcessor;
import socket_installer.SI_parts.io_components.IO.wrapper.server.ConnectedClientInputStreamWrapper;
import socket_installer.SI_parts.io_components.IO.wrapper.server.ConnectedClientOutputStreamWrapper;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
            IOProcessor.getIoProcessor().checkBytesReadClientConnected(actions,this);
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
        ioHolder.setInputStream(new ConnectedClientInputStreamWrapper( new BufferedInputStream(socket.getInputStream()) ));
        ioHolder.setOutputStream(new ConnectedClientOutputStreamWrapper( new BufferedOutputStream(socket.getOutputStream()) ));
    }
}

