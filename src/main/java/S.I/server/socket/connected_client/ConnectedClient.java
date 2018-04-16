package S.I.server.socket.connected_client;

import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClient implements SocketModel {

    private Socket clientSocket;
    private ConnectedClientConfiguration connectedClientConfiguration;

    public ConnectedClient(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void setConnectedClientConfiguration(ConnectedClientConfiguration connectedClientConfiguration) {
        this.connectedClientConfiguration = connectedClientConfiguration;
    }

    @Override
    public void activateSocket() throws IOException, AbstractSpecificException {
        boolean clientStatus = true;
        connectedClientConfiguration.setSocketOnlineStatus(clientStatus);

        while(clientStatus){
            System.out.println("Client is listening" + clientSocket.getInputStream().available());
        }
    }

    @Override
    public void deactivateSocket() throws IOException, AbstractSpecificException {

    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return connectedClientConfiguration;
    }

}
