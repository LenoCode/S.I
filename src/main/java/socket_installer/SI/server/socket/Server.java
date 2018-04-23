package socket_installer.SI.server.socket;

import socket_installer.SI.socket_creation.server.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.CreatedSocketModel;
import socket_installer.SI_behavior.interfaces.sockets.SocketConfiguration;

import java.io.IOException;
import java.net.*;

public class Server extends BasicSocket {

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

        while(serverStatus){
            Socket socketConnectedToServer = serverSocket.accept();
            createNewThreadForClient(socketConnectedToServer);
        }
    }
    private void createNewThreadForClient(Socket clientConnected)throws IOException, SocketExceptions{
        clientConnected.setSoTimeout(serverConfiguration.getTimeout());
        CreatedSocketModel createdClientModel = ClientCreator.createClient(clientConnected);
        createdClientModel.runSocket();
    }
}