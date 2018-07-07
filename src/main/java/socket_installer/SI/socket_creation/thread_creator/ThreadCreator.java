package socket_installer.SI.socket_creation.thread_creator;

import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client.ConnectedClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

import static socket_installer.SI.socket_creation.thread_creator.thread_processor_enums.ThreadProcessorEnum.*;

public class ThreadCreator {


    public Thread createActivationThread(ConnectedClientCreatedSocket connectedClientCreatedSocket){
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
        Thread thread = new Thread(createRunnable(connectedClientCreatedSocket));
        asyncCommunicator.initNewThread(thread.getId());
        return thread;
    }
    public Thread createActiviationThreadWithoutInit(ConnectedClientCreatedSocket connectedClientCreatedSocket){
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
        Thread thread = new Thread(createRunnableWithoutInit(connectedClientCreatedSocket));
        asyncCommunicator.initNewThread(thread.getId());
        return thread;
    }


    private Runnable createRunnable(ConnectedClientCreatedSocket connectedClientCreatedSocket){
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
        return new Runnable() {
            @Override
            public void run() {
                Long id = Thread.currentThread().getId();

                asyncCommunicator.addFlag(id,CLIENT_RECONNECT.getId(),false);
                asyncCommunicator.addFlag(id,  CLIENT_THREAD_IN_PROCESS.getId(),true);
                asyncCommunicator.addFlag(id,WAITING_CLIENT_RECONNECT.getId(),false);

                try {
                    connectedClientCreatedSocket.initSocket();
                    connectedClientCreatedSocket.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        };
    }
    private Runnable createRunnableWithoutInit(ConnectedClientCreatedSocket connectedClientCreatedSocket){
        AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();
        return new Runnable() {
            @Override
            public void run() {
                Long id = Thread.currentThread().getId();

                asyncCommunicator.addFlag(id,CLIENT_RECONNECT.getId(),false);
                asyncCommunicator.addFlag(id,  CLIENT_THREAD_IN_PROCESS.getId(),true);
                asyncCommunicator.addFlag(id,WAITING_CLIENT_RECONNECT.getId(),false);

                try {
                    connectedClientCreatedSocket.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        };
    }
}
