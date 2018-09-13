package socket_installer.SI.server.socket_actions.connection_handler;


import async_communicator.AsyncCommunicator;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.socket_creation.client.ClientSocketCreator;
import socket_installer.SI.socket_creation.thread_creator.thread_processor_enums.ThreadProcessorEnum;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;
import java.io.IOException;
import java.net.Socket;

public class NewConnectionHandler {
    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();

    public synchronized void handleConnection(NotificationerActions notificationer, Socket clientConnected, int timeout) throws IOException, SocketExceptions{
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
        System.out.println("Handling connection ----->");
        ConnectedClient clientSocket = sessionTracker.checkIfSocketExists(clientConnected.getInetAddress().getHostAddress());

        if (clientSocket == null){
            setupNewConnection(notificationer,clientConnected,timeout);
        }else{
            setupOldConnectionThread(clientConnected,clientSocket);
        }
    }

    private void setupNewConnection(NotificationerActions notificationer, Socket clientConnected,int timeout)throws IOException, SocketExceptions{
        System.out.println("setup new connection");
        clientConnected.setSoTimeout(timeout);
        notificationer.callExternalInitializator();
        Thread threadOfConnectedClient = ClientSocketCreator.createConnectedClientCreatedSocket(notificationer,clientConnected,timeout);
        threadOfConnectedClient.start();
    }

    private void setupOldConnectionThread(Socket clientConnected, ClientSocket clientSocket){
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    setupOldConnection(clientConnected,clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        }).start();
    }

    private void setupOldConnection(Socket clientConnected,ClientSocket clientSocket)throws  IOException,SocketExceptions{
        System.out.println("setup old connection,  repacing socket");
        clientSocket.replaceSocket(clientConnected);
        ClientConfiguration clientConfiguration = (ClientConfiguration) clientSocket.getSocketConfiguration();
        Long threadId = clientConfiguration.getThreadId();

        if (threadId != null){
            clientConnected.setSoTimeout(clientConfiguration.getTimeoutIncrease());
            checkCurrentThreadStatus(clientSocket);
        }else{
            continueWithNewThread(clientSocket);
        }
    }
    private void checkCurrentThreadStatus(ClientSocket clientSocket){
        ClientConfiguration clientConfiguration = (ClientConfiguration) clientSocket.getSocketConfiguration();
        Long threadId;

        while((threadId = clientConfiguration.getThreadId()) != null){

            if (checkIfThreadInProcess(threadId)){
               if (checkIfThreadWaitsForReconnect(threadId)){
                   continueWithCurrentThread(threadId);
                   return;
               }
            }
        }
        continueWithNewThread(clientSocket);
    }
    private boolean checkIfThreadInProcess(Long threadId){
        try{
            return asyncCommunicator.getFlag(threadId,ThreadProcessorEnum.CLIENT_THREAD_IN_PROCESS.getId());
        }catch (NullPointerException e){
            return false;
        }
    }
    private boolean checkIfThreadWaitsForReconnect(Long threadId){
       return asyncCommunicator.getFlag(threadId,ThreadProcessorEnum.WAITING_CLIENT_RECONNECT.getId());
    }

    private void continueWithCurrentThread(Long threadId){
        asyncCommunicator.addFlag(threadId,ThreadProcessorEnum.CLIENT_RECONNECT.getId(),true);
    }
    private void continueWithNewThread(ClientSocket clientSocket){
        Thread threadOfConnectedClient = ClientSocketCreator.injectToExistingConnectedClient(clientSocket);
        threadOfConnectedClient.start();
    }

}
