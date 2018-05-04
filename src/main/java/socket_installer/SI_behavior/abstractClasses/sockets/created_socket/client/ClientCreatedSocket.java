package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket_exception.specific_exceptions.connection_break_exception.ClientConnectionAbortException;
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
        boolean messageNotSentStatus = true;

        while(messageNotSentStatus){
            try{
                System.out.println("ŠALJEM MESSAGE");
                Client client = (Client) basicSocket;
                client.sendMessage(message);
                messageNotSentStatus = false;
            }catch (ClientConnectionAbortException clientAbortException){
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
