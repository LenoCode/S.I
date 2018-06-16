package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;

import socket_installer.SI_parts.IO.wrapper.client.ClientInputStreamWrapper;
import socket_installer.SI_parts.IO.wrapper.client.ClientOutputStreamWrapper;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.actionHolder.ActionHolder;
import socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.client_impl.ClientReadStatusProcessor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends ClientSocket {

    public Client(Socket clientSocket) {
        super(clientSocket);
        actions = new ActionHolder(new ClientReadStatusProcessor());
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        //CommunicationProcessor.getCommunicationProcessor().activateReadProcess(this);
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
    }

    public void reconnectSocket() throws IOException, SocketExceptions{
        deactivateSocket();
        Socket socket = new Socket(socketConfiguration.getIpAddress(),socketConfiguration.getPort());
        replaceSocket(socket);
    }

    @Override
    public void replaceSocket(Socket socket) throws IOException, SocketExceptions {
        this.socket = socket;
        ((Socket) this.socket).setSoTimeout(socketConfiguration.getTimeout());
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
