package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;


import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;
import socket_installer.SI_parts.protocol.protocol_object.defined_protocol.defined_automated_responder.DefinedAutomatedResponder;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;


public class ConnectedClientProcessor extends PacketProcessor {


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
                throw new ConnectedClientTimeoutException();
        }
    }
    private boolean isPacketSendingInitilazed(){
        return true;
    }
    private boolean isPacketSendingFirstTrySecondTry(){
        return true;
    }
    private boolean isPacketSendingThirdTry() throws IOException, SocketExceptions{
        throw new ConnectedClientClosedException();
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
            case SOCKET_CLOSED:
                return isDataIncompleteSocketClosed(packetHolder);
            case DATA_RECV_FAILED:
                throw new ConnectedClientClosedException();
            default:
                throw new ConnectedClientClosedException();
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

    private boolean isDataIncompleteSocketClosed(PacketHolder packetHolder){
        ConnectedClient connectedClient = (ConnectedClient) packetHolder.getClientSocket();
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
        sessionTracker.removeConnection(connectedClient);
        return false;
    }

    private boolean isDataIncompleteComplete(){
        return false;
    }
}
