package S.I.server.socket;

import S.I.socket_creation.server.connectedClient.ConnectedClientCreator;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.*;

public class Server implements SocketModel {

    private ServerSocket serverSocket;
    private ServerConfiguration serverConfiguration;
    private SessionTracker sessionTracker;

    public void setServerConfiguration(ServerConfiguration serverConfiguration){
        this.serverConfiguration = serverConfiguration;
        this.sessionTracker = new SessionTracker();
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
        CreatedSocketModel createdClientModel = ConnectedClientCreator.createConnectedClient(clientConnected, sessionTracker);
        createdClientModel.runSocket();
    }
}