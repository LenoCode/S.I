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


    public boolean sendMessageToServer(String message){
        try{
            message = String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message);
            Client client = (Client) basicSocket;
            PacketRequest packetRequest = new PacketRequest(client,message);
            client.sendMessage(packetRequest);
            return true;
        }catch (ClientConnectionAbortException clientAbortException){
            clientAbortException.handleException(this.basicSocket);
            return false;
        } catch (SocketExceptions socketExceptions){
            socketExceptions.handleException(basicSocket);
            return false;
        }catch (IOException ioException){
           ioException.printStackTrace();
           return false;
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
