package socket_installer.SI.server.socket;

import socket_installer.SI.socket_creation.server.connectedClient.ConnectedClientCreator;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.CreatedSocketModel;
import socket_installer.SI_behavior.interfaces.sockets.SocketConfiguration;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.*;

public class Server implements SocketModel {

    private ServerSocket serverSocket;
    private ServerConfiguration serverConfiguration;

    public void setServerConfiguration(ServerConfiguration serverConfiguration){
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        createSocket();
        socketMainLoop();
    }

    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        serverSocket.close();
        serverSocket = null;
    }
    @Override
    public SocketConfiguration getSocketConfiguration() {
        return serverConfiguration;
    }


    private void createSocket() throws IOException, SocketExceptions {
        serverSocket = new ServerSocket(serverConfiguration.getPort(), serverConfiguration.getBacklog());
    }

    private void socketMainLoop()throws IOException, SocketExceptions {
        boolean serverStatus = true;
        serverConfiguration.setSocketOnlineStatus(serverStatus);
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
        System.out.println(sessionTracker.toString());

        while(serverStatus){
            Socket socketConnectedToServer = serverSocket.accept();
            createNewThreadForClient(socketConnectedToServer);
        }
    }
    private void createNewThreadForClient(Socket clientConnected)throws IOException, SocketExceptions{
        clientConnected.setSoTimeout(serverConfiguration.getTimeout());
        CreatedSocketModel createdClientModel = ConnectedClientCreator.createConnectedClient(clientConnected);
        createdClientModel.runSocket();
    }
}