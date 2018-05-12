package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientConnectionAbortException;
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
        int messageSentStatus = -1;

        while(messageSentStatus < 1){
            try{
                Client client = (Client) basicSocket;
                client.sendMessage(message);
                messageSentStatus = 1;
            }catch (ClientConnectionAbortException clientAbortException){
                ++messageSentStatus;
                clientAbortException.handleException(this.basicSocket);
            } catch (SocketExceptions socketExceptions){
                //Ovjde treba napraviti exception koji ce izaci iz loopa;
            }catch (IOException ioException){
                //Ovjde treba napraviti exception koji ce izaci iz loopa;
            }
        }
    }
    public void activateSocket(){
        try{
            Client client = (Client) basicSocket;
            client.activateSocket();
        }catch (SocketExceptions socketExceptions) {
            socketExceptions.handleException(this.basicSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
