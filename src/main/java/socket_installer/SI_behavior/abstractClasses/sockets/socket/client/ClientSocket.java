package socket_installer.SI_behavior.abstractClasses.sockets.socket.client;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;
import socket_installer.SI_parts.socket_actions.ActionHolder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public abstract class ClientSocket extends BasicSocket {

    protected IOHolder ioHolder;
    protected ActionHolder actions;


    public ClientSocket(Socket clientSocket){
        this.actions = new ActionHolder();
        this.socket = clientSocket;
    }

    public void replaceSocket(Socket socket) throws IOException,SocketExceptions{
        this.socket = socket;
        ioHolder.getStringBuffer().emptyBuffer();
        ioHolder.setInputStream(new BufferedInputStream(socket.getInputStream()));
        ioHolder.setOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        ((ClientConfiguration)socketConfiguration).setStreamPaused(false);
        socketConfiguration.setSocketOnlineStatus(true);

    }

    @Override
    public void setupSocket() throws IOException,SocketExceptions{
        ClientConfiguration clientConfiguration = (ClientConfiguration) getSocketConfiguration();
        setupIOHolder();
        clientConfiguration.setStreamPaused(false);
        clientConfiguration.setSocketOnlineStatus(true);
    }

    public IOHolder getIOHolder() {
        return ioHolder;
    }

    protected void setupIOHolder() throws IOException, SocketExceptions{
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        ioHolder.setInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        ioHolder.setOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        ioHolder.setStringBuffer(new StringBuffer());
    }
}
