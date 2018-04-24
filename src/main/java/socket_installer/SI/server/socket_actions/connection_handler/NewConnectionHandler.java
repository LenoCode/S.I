package socket_installer.SI.server.socket_actions.connection_handler;

import socket_installer.SI.socket_creation.server.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.CreatedSocketModel;

import java.io.IOException;
import java.net.Socket;

public class NewConnectionHandler {

    public NewConnectionHandler(){

    }
    public void createNewThreadForClient(Socket clientConnected, int timeout) throws IOException, SocketExceptions{
        clientConnected.setSoTimeout(timeout);
        CreatedSocketModel createdClientModel = ClientCreator.createConnectedClient(clientConnected);
        createdClientModel.runSocket();

    }

}
