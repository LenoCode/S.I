package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;

public abstract class ConnectedClientCreatedSocket implements CreatedSocketModel {
    protected ClientSocket basicSocket;
}
