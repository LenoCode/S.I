package socket_installer.SI.server.socket_actions.connection_handler;


import socket_installer.SI.socket_creation.server.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.CreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class NewConnectionHandler {

    private final SessionTracker sessionTracker;

    public NewConnectionHandler(){
        sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
    }
    public void handleConnection(Notificationer notificationer, Socket clientConnected, int timeout) throws IOException, SocketExceptions{
        clientConnected.setSoTimeout(timeout);
        ClientSocket clientSocket = sessionTracker.checkIfSocketExists(clientConnected.getInetAddress().getHostAddress());

        if (clientSocket == null){
            setupNewConnection(notificationer,clientConnected);
        }else{
            setupOldConnection(clientConnected,clientSocket);
        }
    }

    private void setupNewConnection(Notificationer notificationer,Socket clientConnected)throws IOException, SocketExceptions{
        CreatedSocket createdClientModel = ClientCreator.createConnectedClient(notificationer,clientConnected);
        createdClientModel.runSocket();
    }

    private void setupOldConnection(Socket clientConnected,ClientSocket clientSocket)throws  IOException,SocketExceptions{
        clientSocket.replaceSocket(clientConnected);
        CreatedSocket createdClientModel = ClientCreator.createConnectedClient(clientSocket);
        createdClientModel.runSocket();
    }

}
