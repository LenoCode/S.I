package S.I.server.socket;

import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;
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
    public void activateSocket() throws IOException,AbstractSpecificException {
        createSocket();
        socketMainLoop();
    }

    @Override
    public void deactivateSocket() throws IOException, AbstractSpecificException {
        serverSocket.close();
        serverSocket = null;
    }

    private void socketMainLoop()throws IOException, AbstractSpecificException {
        boolean serverStatus = true;
        serverConfiguration.setSocketOnlineStatus(serverStatus);
        while(serverStatus){
            Socket socketConnectedToServer = serverSocket.accept();

        }
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return serverConfiguration;
    }

    private void createSocket() throws IOException,AbstractSpecificException {
        serverSocket = new ServerSocket(serverConfiguration.getPort(), serverConfiguration.getBacklog());
    }
}