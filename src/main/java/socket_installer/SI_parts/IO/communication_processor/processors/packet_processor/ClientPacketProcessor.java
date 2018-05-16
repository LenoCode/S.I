package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;


import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;

import java.io.IOException;

import static socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums.*;

class ClientPacketProcessor extends PacketProcessor {



    @Override
    public boolean sendPacket(PacketHolder packetHolder) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetHolder.getClientSocket();
        IOHolder ioHolder = clientSocket.getIOHolder();
        String message = ((PacketRequest)packetHolder).getRequest();

        while(isPacketSending(packetHolder)){
            if (packetHolder.getPacketStatus() == INITILIAZED){
                sendData(message, ioHolder.getOutputStream());
            }
            packetStatusProcessor.checkPacketStatus(packetHolder);
        }
        System.out.println(ioHolder.getStringBuffer().getString());
        return true;
    }

    @Override
    public void checkInputStreamData(PacketHolder packetHolder) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetHolder.getClientSocket();

        while(isDataUncomplete(packetHolder)){
            packetStatusProcessor.checkPacketStatus(packetHolder);
        }
        packetHolder.getClientSocket().getActions().getBytesResponder().sendBytesRecv(clientSocket.getIOHolder());
        System.out.println("king kong je tu  "+clientSocket.getIOHolder().getStringBuffer().getString());

    }

    @Override
    public boolean isPacketSending(PacketHolder packetHolder) throws IOException, SocketExceptions {
        Client client = (Client) packetHolder.getClientSocket();
        System.out.println(packetHolder.getPacketStatus());
        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return isPacketSendingInitilazed();
            case FIRST_TRY:
                return isPacketSendingFirstTrySecondTry();
            case SECOND_TRY:
                return isPacketSendingFirstTrySecondTry();
            case THIRD_TRY:
                throw new ClientClosedException();
            case DATA_INCOMPLETE:
                return isPacketSendingDataIncomplete();
            case SOCKET_CLOSED:
                return isPacketSendingDataSocketClosed(packetHolder,client);
            case BYTES_SENT_SUCCESS:
                return isPacketSendingDataBytesSuccess();
            default:
                throw new ClientClosedException();
        }
    }
    private boolean isPacketSendingInitilazed(){
        return true;
    }
    private boolean isPacketSendingFirstTrySecondTry()throws IOException,SocketExceptions{
        return true;
    }
    private boolean isPacketSendingDataIncomplete(){
        return true;
    }
    private boolean isPacketSendingDataSocketClosed(PacketHolder packetHolder,Client client)throws IOException, SocketExceptions{
        client.reconnectSocket();
        packetHolder.setPacketStatus(INITILIAZED);
        return true;
    }
    private boolean isPacketSendingDataBytesSuccess(){
        return false;
    }


    @Override
    public boolean isDataUncomplete(PacketHolder packetHolder) throws IOException, SocketExceptions {

        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return isDataIncompleteInitilazed();
            case FIRST_TRY:
                return isDataIncompleteFirstTrySecondTryThirdTry((Client)packetHolder.getClientSocket());
            case SECOND_TRY:
                return isDataIncompleteFirstTrySecondTryThirdTry((Client)packetHolder.getClientSocket());
            case THIRD_TRY:
                return isDataIncompleteFirstTrySecondTryThirdTry((Client)packetHolder.getClientSocket());
            case DATA_INCOMPLETE:
                return isDataIncomplete();
            case DATA_COMPLETE:
                return isDataIncompleteComplete();
            case DATA_RECV_FAILED:
                throw new ConnectedClientTimeoutException();
            default:
                throw new ConnectedClientTimeoutException();
        }
    }

    private boolean isDataIncompleteInitilazed(){
        return true;
    }
    private boolean isDataIncompleteFirstTrySecondTryThirdTry(Client client)throws IOException,SocketExceptions{
        client.reconnectSocket();
        return true;
    }
    private boolean isDataIncomplete(){
        return true;
    }
    private boolean isDataIncompleteComplete(){
        return false;
    }
}
