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
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class ClientCreator {

    public static ClientCreatedSocket createClient(NotificationerActions notificationer, Socket socket,int timeout){
        return new ClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException,SocketExceptions {
                ClientConfiguration clientConfiguration = new ClientConfiguration(socket);
                clientConfiguration.setTimeout(timeout);
                basicSocket = new Client(socket);
                basicSocket.setSocketConfiguration(clientConfiguration);
                basicSocket.setNotificationer(notificationer);
                basicSocket.setupSocket();
                socket.setSoTimeout(timeout);

                notificationer.setClientSocket((ClientSocket) basicSocket);
                for (DataTradeModel dataTradeModel : notificationer.getObjects()){
                    dataTradeModel.setClientSocket((ClientSocket) basicSocket);
                    dataTradeModel.injectExternalContext(notificationer.getExternalContext());
                }
            }
            @Override
            public void closeProgram() {
                try {
                    CommunicationProcessor.MainProcessor().sendData((ClientSocket)basicSocket,TechnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes());
                    basicSocket.getSocketConfiguration().setSocketOnlineStatus(false);
                    ProgramLoopWrapper.setProgrammRunning(false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }

        };
    }

    public static ConnectedClientCreatedSocket createConnectedClient(NotificationerActions notificationer, Socket socketConnected,int timeout){
        return new ConnectedClientCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {
                ClientConfiguration connectedClientConfiguration = new ClientConfiguration(socketConnected);
                connectedClientConfiguration.setTimeout(timeout);
                basicSocket= new ConnectedClient(socketConnected);
                basicSocket.setSocketConfiguration(connectedClientConfiguration);
                basicSocket.setNotificationer(notificationer);
                basicSocket.setupSocket();

                SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
                sessionTracker.addNewConnection((ConnectedClient) basicSocket);

                ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                notificationer.setClientSocket((ClientSocket) basicSocket);
                for (DataTradeModel dataTradeModel : notificationer.getObjects()){
                    dataTradeModel.setClientSocket((ClientSocket) basicSocket);
                    dataTradeModel.injectExternalContext(notificationer.getExternalContext());
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connectedClientWrappedLoop.activateWrappedLoop(basicSocket);
                        } catch (NoSolutionForException e) {
                            e.printStackTrace();
                        }
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
                                try {
                                    connectedClientWrappedLoop.activateWrappedLoop(clientSocket);
                                } catch (NoSolutionForException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }).start();
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



