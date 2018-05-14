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

        sendData(message, ioHolder.getOutputStream());
        while(isPacketSending(packetHolder)){
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
        System.out.println(packetHolder.getPacketStatus() + "      "+client.getIOHolder().getStringBuffer().getString());
        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return true;
            case FIRST_TRY: {
                client.reconnectSocket();
                return true;
            }
            case SECOND_TRY:{
                client.reconnectSocket();
                return true;
            }
            case THIRD_TRY:
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
