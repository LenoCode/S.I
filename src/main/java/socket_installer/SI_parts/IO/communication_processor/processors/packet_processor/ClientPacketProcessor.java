package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;


import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.protocol.protocol_object.defined_protocol.defined_automated_responder.DefinedAutomatedResponder;


import java.io.IOException;


import static socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums.*;

public class ClientPacketProcessor extends PacketProcessor {



    @Override
    public boolean isPacketSending(PacketHolder packetHolder) throws IOException, SocketExceptions {
        Client client = (Client) packetHolder.getClientSocket();
        switch (packetHolder.getPacketStatus()){
            case INITILIAZED:
                return isPacketSendingInitilazed();
            case REINITILIAZED:
                isPacketSendingReinitialized();
            case FIRST_TRY:
                return isPacketSendingFirstTrySecondTry();
            case SECOND_TRY:
                return isPacketSendingFirstTrySecondTry();
            case THIRD_TRY:
                isPacketSendingThirdTry();
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
    private boolean isPacketSendingReinitialized() throws IOException, SocketExceptions{
        throw new ClientClosedException();
    }
    private boolean isPacketSendingFirstTrySecondTry()throws IOException,SocketExceptions{
        return true;
    }
    private boolean isPacketSendingThirdTry() throws IOException,SocketExceptions{
        throw new ClientClosedException();
    }
    private boolean isPacketSendingDataIncomplete(){
        return true;
    }
    private boolean isPacketSendingDataSocketClosed(PacketHolder packetHolder,Client client)throws IOException, SocketExceptions{
        client.reconnectSocket();
        packetHolder.setPacketStatus(REINITILIAZED);
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
                throw new ClientClosedException();
            default:
                throw new ClientClosedException();
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
