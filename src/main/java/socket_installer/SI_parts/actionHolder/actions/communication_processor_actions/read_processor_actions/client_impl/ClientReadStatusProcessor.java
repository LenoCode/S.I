package socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.client_impl;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;

public class ClientReadStatusProcessor implements ReadStatusProcessorModel {
    private ProcessorEnums streamOpenStatus = ProcessorEnums.STREAM_CLOSED;
    private ProcessorEnums readStatus;

    @Override
    public boolean checkStreamStatus(ClientSocket clientSocket) throws SocketExceptions, IOException {
        System.out.println(readStatus);
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
                CommunicationProcessor.MainProcessor().sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol().getBytes());
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

    private boolean reconnectSocket(Client client) throws IOException, SocketExceptions {
        client.reconnectSocket();
        return true;
    }
}
