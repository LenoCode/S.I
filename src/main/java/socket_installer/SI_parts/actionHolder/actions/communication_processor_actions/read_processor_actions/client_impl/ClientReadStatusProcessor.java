package socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.client_impl;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;

import java.io.IOException;

public class ClientReadStatusProcessor implements ReadStatusProcessorModel {
    private ProcessorsEnums streamOpenStatus = ProcessorsEnums.STREAM_CLOSED;
    private ProcessorsEnums readStatus;

    @Override
    public boolean checkStreamStatus(ClientSocket clientSocket) throws SocketExceptions, IOException {
        switch (readStatus){
            case FIRST_TRY:
                return reconnectSocket((Client) clientSocket);
            case SECOND_TRY:
                return reconnectSocket((Client) clientSocket);
            case THIRD_TRY:
                return reconnectSocket((Client) clientSocket);
            case FOURTH_TRY:
                return reconnectSocket((Client) clientSocket);
            case FIFTH_TRY:
                throw new ClientClosedException();
            case DATA_INCOMPLETE:
                return true;
            case DATA_COMPLETE:
                return false;
            default:
                throw new ClientClosedException();
        }
    }

    @Override
    public boolean checkIfStreamOpen() {
        return streamOpenStatus == ProcessorsEnums.STREAM_OPEN;
    }

    @Override
    public ProcessorsEnums checkReadStatus() {
        return readStatus;
    }

    @Override
    public void setCheckReadStatus(ProcessorsEnums readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public void setStreamOpenStatus(ProcessorsEnums processorsEnums) {
        streamOpenStatus = processorsEnums;
    }

    private boolean reconnectSocket(Client client) throws IOException, SocketExceptions {
        System.out.println("Reconnect socket");
        client.reconnectSocket();
        return true;
    }
}
