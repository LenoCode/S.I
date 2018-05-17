package socket_installer.SI.server.socket;

import socket_installer.SI.server.socket_actions.connection_handler.NewConnectionHandler;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.*;

public class Server extends BasicSocket {

    private NewConnectionHandler newConnectionHandler;

    public Server(NewConnectionHandler newConnectionHandler){
        this.newConnectionHandler = newConnectionHandler;
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        ServerSocket serverSocket = (ServerSocket)socket;
        ServerConfiguration serverConfiguration = (ServerConfiguration) getSocketConfiguration();
        //SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();

        while(serverConfiguration.isSocketOnline()){
            Socket socketConnectedToServer = serverSocket.accept();
            System.out.println("New client connected");
            newConnectionHandler.handleConnection(getNotificationer(),socketConnectedToServer,serverConfiguration.getTimeout());
        }
    }

    @Override
    public void setupSocket() throws IOException, SocketExceptions{
        if (socket == null) {
            ServerConfiguration serverConfiguration = (ServerConfiguration) getSocketConfiguration();
            socket = new ServerSocket(serverConfiguration.getPort(), serverConfiguration.getBacklog());
            serverConfiguration.setSocketOnlineStatus(true);
        }
    }

}