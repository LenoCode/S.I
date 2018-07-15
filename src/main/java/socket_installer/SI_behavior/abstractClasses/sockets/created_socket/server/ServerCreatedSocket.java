package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;


public abstract class ServerCreatedSocket implements CreatedSocketModel {
    protected Server basicSocket;

    public boolean isServerRunning(){
        return basicSocket.getSocketConfiguration().isSocketOnline();
    }
}
