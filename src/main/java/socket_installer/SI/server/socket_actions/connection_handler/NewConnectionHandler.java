package socket_installer.SI.server.socket_actions.connection_handler;

import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.socket_creation.server.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.CreatedSocket;
import socket_installer.SI_behavior.interfaces.io_observer.notification_handler.NotificationHandler;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;

import java.io.IOException;
import java.net.Socket;

public class NewConnectionHandler {

    public NewConnectionHandler(){

    }
    public void createNewThreadForClient(NotificationHandler notificationHandler,Socket clientConnected, int timeout) throws IOException, SocketExceptions{
        clientConnected.setSoTimeout(timeout);
        CreatedSocket createdClientModel = ClientCreator.createConnectedClient(notificationHandler,clientConnected);
        createdClientModel.runSocket();
    }

}
