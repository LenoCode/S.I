package socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.connected_client_impl;

import async_communicator.AsyncCommunicator;
import socket_installer.SI.socket_creation.thread_creator.thread_processor_enums.ThreadProcessorEnum;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientClosedException;


import java.io.IOException;

public class ConnectedClientReadStatusProcessor implements ReadStatusProcessorModel {
    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
    private ProcessorEnums streamOpenStatus = ProcessorEnums.STREAM_CLOSED;
    private ProcessorEnums readStatus;

    @Override
    public boolean checkStreamStatus(ClientSocket clientSocket) throws SocketExceptions,IOException {
        System.out.println("CHECK STREAM STATUS  -> "+readStatus);
        switch (readStatus){
            case FIRST_TRY:
                return waitForSocketToReconnect();
            case SECOND_TRY:
                return waitForSocketToReconnect();
            case THIRD_TRY:
                return waitForSocketToReconnect();
            case FOURTH_TRY:
                return waitForSocketToReconnect();
            case FIFTH_TRY:
                throw new ConnectedClientClosedException();
            case DATA_INCOMPLETE:
                return true;
            case DATA_COMPLETE:
                return false;
            default:
                throw new NoSolutionForException("Timeout exception");
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

    private boolean waitForSocketToReconnect() throws ConnectedClientClosedException {
        int timeout = 100;
        activateReconnectProcess();

        while(asyncCommunicator.getFlag(Thread.currentThread().getId(),ThreadProcessorEnum.CLIENT_RECONNECT.getId()) == false && timeout != 0){
            System.out.println("WAITING FOR RECONNECT   " + Thread.currentThread().getId() );
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeout -= 10;
        }
        deactivateReconnectProcess();
        return true;
    }
    private void activateReconnectProcess(){
        asyncCommunicator.addFlag(Thread.currentThread().getId(),ThreadProcessorEnum.WAITING_CLIENT_RECONNECT.getId(),true);
    }
    private void deactivateReconnectProcess(){
        asyncCommunicator.addFlag(Thread.currentThread().getId(),ThreadProcessorEnum.WAITING_CLIENT_RECONNECT.getId(),false);
        asyncCommunicator.addFlag(Thread.currentThread().getId(),ThreadProcessorEnum.CLIENT_RECONNECT.getId(),false);
    }

    private void connectToClient(ClientSocket clientSocket) throws IOException, SocketExceptions {
        clientSocket.reconnectSocket();
    }

}
