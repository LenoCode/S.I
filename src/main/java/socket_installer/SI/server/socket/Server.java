package socket_installer.SI.server.socket;

import socket_installer.SI.server.socket_actions.connection_handler.NewConnectionHandler;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;

import java.io.IOException;
import java.net.*;

public class Server extends BasicSocket {

    private ServerSocket serverSocket;
    private ServerConfiguration serverConfiguration;
    private NewConnectionHandler newConnectionHandler;

    public Server(ServerConfiguration serverConfiguration, NewConnectionHandler newConnectionHandler){
        this.serverConfiguration = serverConfiguration;
        this.newConnectionHandler = newConnectionHandler;
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        while(serverConfiguration.isSocketOnline()){
            Socket socketConnectedToServer = serverSocket.accept();
            newConnectionHandler.createNewThreadForClient(notificationHandler,socketConnectedToServer,serverConfiguration.getTimeout());
        }
    }
    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        serverSocket.close();
        serverSocket = null;
    }

    @Override
    public void setupSocket() throws IOException, SocketExceptions{
        if (serverSocket == null) {
            serverSocket = new ServerSocket(serverConfiguration.getPort(), serverConfiguration.getBacklog());
            serverConfiguration.setSocketOnlineStatus(true);
        }
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return serverConfiguration;
    }

}