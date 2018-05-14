package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;

import java.io.IOException;

import static socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums.*;

public class ConnectedClientProcessor extends PacketProcessor {



    @Override
    public boolean sendPacket(PacketHolder packetRequest) throws IOException, SocketExceptions {

        ClientSocket clientSocket = packetRequest.getClientSocket();
        IOHolder ioHolder = clientSocket.getIOHolder();
        String message = ((PacketRequest)packetRequest).getRequest();

        while(isPacketSending(packetRequest)){
            sendData(message, ioHolder.getOutputStream());
            packetStatusProcessor.checkPacketStatus(packetRequest);
        }
        System.out.println(ioHolder.getStringBuffer().getString());
        return true;
    }

    @Override
    public void checkInputStreamData(PacketHolder packetResponse) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetResponse.getClientSocket();
        while(isDataUncomplete(packetResponse)){
            packetStatusProcessor.checkPacketStatus(packetResponse);
        }
        packetResponse.getClientSocket().getActions().getBytesResponder().sendBytesRecv(clientSocket.getIOHolder());
        System.out.println("king kong je tu  "+clientSocket.getIOHolder().getStringBuffer().getString());

    }

    @Override
    public boolean isPacketSending(PacketHolder packetHolder) throws IOException, SocketExceptions {

        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return true;
            case FIRST_TRY:
                throw new ClientClosedException();
            case DATA_INCOMPLETE:
                return true;
            case BYTES_SENT_SUCCESS:
                return false;
            default:
                throw new ClientClosedException();
        }
    }

    @Override
    public boolean isDataUncomplete(PacketHolder packetHolder) throws IOException, SocketExceptions {

        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return true;
            case FIRST_TRY:
                return true;
            case SECOND_TRY:
                return true;
            case THIRD_TRY:
                return true;
            case DATA_INCOMPLETE:
                return true;
            case DATA_COMPLETE:
                return false;
            case DATA_RECV_FAILED:
                throw new ConnectedClientTimeoutException();
            default:
                throw new ConnectedClientTimeoutException();
        }
    }
}
