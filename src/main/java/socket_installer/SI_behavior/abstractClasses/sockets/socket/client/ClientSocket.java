package socket_installer.SI_behavior.abstractClasses.sockets.socket.client;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_parts.io_components.IO.IOHolder;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer.StringBuffer;
import socket_installer.SI_parts.socket_actions.ActionHolder;

import java.io.IOException;
import java.net.Socket;


public abstract class ClientSocket extends BasicSocket {

    protected IOHolder ioHolder;
    protected ActionHolder actions;


    public ClientSocket(Socket clientSocket){
        this.actions = new ActionHolder();
        this.socket = clientSocket;
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
        ioHolder.setInStream(clientSocket.getInputStream());
        ioHolder.setOutStream(clientSocket.getOutputStream());
        ioHolder.setStringBuffer(new StringBuffer());
    }
}
