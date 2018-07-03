package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;
import java.io.IOException;

public abstract class ClientCreatedSocket implements CreatedSocketModel {
    protected BasicSocket basicSocket;

    public Client getClient(){
        return (Client) basicSocket;
    }

    public boolean isConnectedToServer(){
        return basicSocket.getSocketConfiguration().isSocketOnline();
    }

}
