package socket_installer.SI.socket_creation.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.client.socket_actions.socket_loop.ClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.connected_client.ConnectedClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;


import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class ClientCreator {

    public static ClientCreatedSocket createClient(NotificationerActions notificationer, Socket socket){
        return new ClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException,SocketExceptions {
                ClientConfiguration clientConfiguration = new ClientConfiguration(socket);
                basicSocket = new Client(socket);
                basicSocket.setSocketConfiguration(clientConfiguration);
                basicSocket.setNotificationer(notificationer);
                basicSocket.setupSocket();
            }

            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }

        };
    }

    public static ConnectedClientCreatedSocket createConnectedClient(NotificationerActions notificationer, Socket socketConnected){
        return new ConnectedClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {
                ClientConfiguration connectedClientConfiguration = new ClientConfiguration(socketConnected);
                ConnectedClient connectedClient = new ConnectedClient(socketConnected);
                connectedClient.setSocketConfiguration(connectedClientConfiguration);

                SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
                sessionTracker.addNewConnection(connectedClient);

                ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                basicSocket = connectedClient;
                basicSocket.setNotificationer(notificationer);

                connectedClient.setupSocket();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectedClientWrappedLoop.activateWrappedLoop(connectedClient);
                    }
                }).start();
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }

        };
    }
    public static ConnectedClientCreatedSocket createConnectedClient(ClientSocket clientSocket){
        return new ConnectedClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                connectedClientWrappedLoop.activateWrappedLoop(clientSocket);
                            }
                        }).start();

                    }
                }).start();
            }

            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }
        };
    }
}



