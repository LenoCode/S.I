package socket_installer.SI.socket_creation.client.connected_client_creator;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.client.socket_actions.socket_loop.ClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client.ConnectedClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClientCreator {

    public ConnectedClientCreatedSocket createConnectedClientCreatedSocket(NotificationerActions notificationerActions,Socket socket,int timeout){
        return new ConnectedClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {
                ClientConfiguration connectedClientConfiguration = new ClientConfiguration(socket);
                connectedClientConfiguration.setThreadId(Thread.currentThread().getId());
                connectedClientConfiguration.setTimeout(timeout);

                basicSocket= new ConnectedClient(socket);
                basicSocket.setSocketConfiguration(connectedClientConfiguration);
                basicSocket.setNotificationer(notificationerActions);
                basicSocket.setupSocket();

                SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
                sessionTracker.addNewConnection((ConnectedClient) basicSocket);

                ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                notificationerActions.setClientSocket((ClientSocket) basicSocket);
                for (DataTradeModel dataTradeModel : notificationerActions.getObjects()){
                    dataTradeModel.setClientSocket((ClientSocket) basicSocket);
                    dataTradeModel.injectExternalContext(notificationerActions.getExternalContext());
                }

                connectedClientWrappedLoop.activateWrappedLoop(basicSocket);
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }

        };
    }

    public ConnectedClientCreatedSocket injectSocketToConnectedClient(ClientSocket socket){
        return new ConnectedClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {
                ClientConfiguration clientConfiguration = (ClientConfiguration) socket.getSocketConfiguration();
                clientConfiguration.setThreadId(Thread.currentThread().getId());
                ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();
                connectedClientWrappedLoop.activateWrappedLoop(socket);
            }

            @Override
            public void closeProgram() {
                try {
                    ProgramLoopWrapper.setProgrammRunning(false);
                    basicSocket.deactivateSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        };
    }
}
