package socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.connected_client_impl;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientClosedException;

import java.io.IOException;

public class ConnectedClientReadStatusProcessor implements ReadStatusProcessorModel {
    private ProcessorEnums streamOpenStatus = ProcessorEnums.STREAM_CLOSED;
    private ProcessorEnums readStatus;

    @Override
    public boolean checkStreamStatus(ClientSocket clientSocket) throws SocketExceptions,IOException {
        switch (readStatus){
            case FIRST_TRY:
                return true;
            case SECOND_TRY:
                return true;
            case THIRD_TRY:
                return true;
            case FOURTH_TRY:
                return true;
            case FIFTH_TRY:
                throw new ConnectedClientClosedException();
            case DATA_INCOMPLETE:
                return true;
            case DATA_COMPLETE:
                return false;
            default:
                throw new ConnectedClientClosedException();
        }
    }


    @Override
    public boolean checkIfStreamOpen() {
        return streamOpenStatus == ProcessorEnums.STREAM_OPEN;
    }

    @Override
    public ProcessorEnums getStreamClosingStatus() {
        return streamOpenStatus;
    }

    @Override
    public ProcessorEnums checkReadStatus() {
        return readStatus;
    }

    @Override
    public void setCheckReadStatus(ProcessorEnums readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public void setStreamOpenStatus(ProcessorEnums processorsEnums) {
        streamOpenStatus = processorsEnums;
    }

}
