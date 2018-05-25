package socket_installer.SI.server.socket_actions.connection_handler;


import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client.ConnectedClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class NewConnectionHandler {


    public void handleConnection(NotificationerActions notificationer, Socket clientConnected, int timeout) throws IOException, SocketExceptions{
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();

        clientConnected.setSoTimeout(timeout);
        ConnectedClient clientSocket = sessionTracker.checkIfSocketExists(clientConnected.getInetAddress().getHostAddress());

        if (clientSocket == null){
            setupNewConnection(notificationer,clientConnected);
        }else{
            setupOldConnection(clientConnected,clientSocket);
        }
    }

    private void setupNewConnection(NotificationerActions notificationer, Socket clientConnected)throws IOException, SocketExceptions{
        ConnectedClientCreatedSocket createdClientModel = ClientCreator.createConnectedClient(notificationer,clientConnected);
        createdClientModel.runSocket();
    }

    private void setupOldConnection(Socket clientConnected,ClientSocket clientSocket)throws  IOException,SocketExceptions{
        clientSocket.replaceSocket(clientConnected);
        ConnectedClientCreatedSocket createdClientModel = ClientCreator.createConnectedClient(clientSocket);
        createdClientModel.runSocket();
    }

}
