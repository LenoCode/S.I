package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;


import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;

import java.io.IOException;


public class ConnectedClientProcessor extends PacketProcessor {

    @Override
    public boolean sendPacket(PacketHolder packetRequest) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetRequest.getClientSocket();
        IOHolder ioHolder = clientSocket.getIOHolder();
        String message = ((PacketRequest)packetRequest).getRequest();

        while(isPacketSending(packetRequest)){
            if (packetRequest.getPacketStatus() == ProcessorsEnums.INITILIAZED){
                sendData(message, ioHolder.getOutputStream());
            }
            packetStatusProcessor.checkPacketStatus(packetRequest);
        }
        return true;
    }

    @Override
    public boolean checkInputStreamData(PacketHolder packetResponse) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetResponse.getClientSocket();
        while(isDataUncomplete(packetResponse)){
            packetStatusProcessor.checkPacketStatus(packetResponse);
        }
        packetResponse.getClientSocket().getActions().getBytesResponder().sendBytesRecv(clientSocket.getIOHolder());
        return true;

    }




    @Override
    public boolean isPacketSending(PacketHolder packetHolder) throws IOException, SocketExceptions {
        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return isPacketSendingInitilazed();
            case FIRST_TRY:
                return isPacketSendingFirstTrySecondTry();
            case SECOND_TRY:
                return isPacketSendingFirstTrySecondTry();
            case THIRD_TRY:
                return isPacketSendingThirdTry();
            case DATA_INCOMPLETE:
                return isPacketSendingDataIncomplete();
            case BYTES_SENT_SUCCESS:
                return isPacketSendingDataBytesSuccess();
            default:
                throw new ClientClosedException();
        }
    }
    private boolean isPacketSendingInitilazed(){
        return true;
    }
    private boolean isPacketSendingFirstTrySecondTry(){
        return true;
    }
    private boolean isPacketSendingThirdTry() throws IOException, SocketExceptions{
        throw new ClientClosedException();
    }
    private boolean isPacketSendingDataIncomplete(){
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
                return isDataIncompleteFirstTrySecondTry();
            case SECOND_TRY:
                return isDataIncompleteFirstTrySecondTry();
            case THIRD_TRY:
                return isDataIncompleteThirdTry();
            case DATA_INCOMPLETE:
                return isDataIncompleteUncomplete();
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
    private boolean isDataIncompleteFirstTrySecondTry(){
        return true;
    }
    private boolean isDataIncompleteThirdTry()throws IOException, SocketExceptions{
        throw new ConnectedClientTimeoutException();
    }
    private boolean isDataIncompleteUncomplete(){
        return true;
    }

    private boolean isDataIncompleteComplete(){
        return false;
    }
}
