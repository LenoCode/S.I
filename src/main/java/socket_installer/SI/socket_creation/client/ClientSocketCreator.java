package socket_installer.SI.socket_creation.client;


import socket_installer.SI.socket_creation.client.client_creator.ClientCreator;
import socket_installer.SI.socket_creation.client.connected_client_creator.ConnectedClientCreator;
import socket_installer.SI.socket_creation.thread_creator.ThreadCreator;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client.ConnectedClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;

import java.net.Socket;

public class ClientSocketCreator {
    private static final ClientCreator clientCreator = new ClientCreator();
    private static final ConnectedClientCreator connectedClientCreator = new ConnectedClientCreator();
    private static final ThreadCreator threadCreator = new ThreadCreator();

    public static ClientCreatedSocket createClientCreatedSocket(NotificationerActions notificationerActions, Socket socket, int timeout){
        ClientCreatedSocket clientCreatedSocket = clientCreator.createClientCreatedSocket(notificationerActions,socket,timeout);
        return clientCreatedSocket;
    }
    public static Thread createConnectedClientCreatedSocket(NotificationerActions notificationerActions,Socket socket,int timeout){
        ConnectedClientCreatedSocket connectedClientCreatedSocket = connectedClientCreator.createConnectedClientCreatedSocket(notificationerActions,socket,timeout);
        return threadCreator.createActivationThread(connectedClientCreatedSocket);
    }
    public static Thread injectToExistingConnectedClient(ClientSocket clientSocket){
        ConnectedClientCreatedSocket connectedClientCreatedSocket = connectedClientCreator.injectSocketToConnectedClient(clientSocket);
        return threadCreator.createActiviationThreadWithoutInit(connectedClientCreatedSocket);
    }
}



