package S.I.server.socket;

import S.I.socket_creation.client.ConnectedClientCreator;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;

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
        while(serverStatus){
            Socket socketConnectedToServer = serverSocket.accept();
            createNewThreadForClient(socketConnectedToServer);

        }
    }
    private void createNewThreadForClient(Socket clientConnected){
        CreatedSocketModel createdClientModel = ConnectedClientCreator.createConnectedClient(clientConnected);
        createdClientModel.runSocket();
    }
}