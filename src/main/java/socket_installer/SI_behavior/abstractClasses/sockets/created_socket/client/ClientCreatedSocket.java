package socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientConnectionAbortException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.io.IOException;

public abstract class ClientCreatedSocket implements CreatedSocketModel {
    protected BasicSocket basicSocket;

    public Client getClient(){
        return (Client) basicSocket;
    }


    public void sendMessageToServer(String message){
        try{
            message = String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message);
            Client client = (Client) basicSocket;
            PacketRequest packetRequest = new PacketRequest(client,message);
            client.sendMessage(packetRequest);
        }catch (ClientConnectionAbortException clientAbortException){
            System.out.println("Client abortion");
            clientAbortException.handleException(this.basicSocket);
            System.out.println("Client exception done");
        } catch (SocketExceptions socketExceptions){
            System.out.println("Tu sam a disi ti");
            socketExceptions.printStackTrace();
        }catch (IOException ioException){
            //Ovjde treba napraviti exception koji ce izaci iz loopa;
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
