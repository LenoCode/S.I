package socket_installer.SI.socket_creation.server;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.client.socket_actions.socket_loop.ClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.CreatedSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class ClientCreator {

    public static CreatedSocketModel createClient(Socket socket){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() throws IOException,SocketExceptions {
                ClientConfiguration clientConfiguration = new ClientConfiguration(socket);
                Client connectedClient = new Client(socket);
                connectedClient.setSocketConfiguration(clientConfiguration);

                ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                connectedClient.setupSocket();

                connectedClientWrappedLoop.activateWrappedLoop(connectedClient);

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

    public static CreatedSocket createConnectedClient(Notificationer notificationer, Socket socketConnected){
        return new CreatedSocket() {
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
}



