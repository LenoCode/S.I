package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;

import java.io.IOException;

public abstract class ClientCreatedSocket implements CreatedSocketModel {
    protected BasicSocket basicSocket;

    public Client getClient(){
        return (Client) basicSocket;
    }

    public void sendMessageToServer(String message){
        try{
            Client client = (Client) basicSocket;
            client.sendMessage(message);
            client.activateSocket();
        }catch (SocketExceptions socketExceptions) {

        }catch (IOException ioException){

        }
    }

}
