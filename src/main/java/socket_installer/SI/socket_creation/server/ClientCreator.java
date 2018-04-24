package socket_installer.SI.socket_creation.server;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI.client.socket_actions.socket_loop.ClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;
import java.net.Socket;

public class ClientCreator {

    public static CreatedSocketModel createClient(Socket socket){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() {
                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        Client connectedClient = new Client(socket);
                        ClientConfiguration connectedClientConfiguration = new ClientConfiguration(socket);
                        connectedClient.setClientConfiguration(connectedClientConfiguration);

                        ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                        try {
                            connectedClient.setupSocket();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SocketExceptions socketExceptions) {
                            socketExceptions.printStackTrace();
                        }
                        connectedClientWrappedLoop.activateWrappedLoop(connectedClient);
                    }
                };
                Thread newClientThread = new Thread(runnable);
                newClientThread.start();
            }

            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }

        };
    }

    public static CreatedSocketModel createConnectedClient(Socket socketConnected){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() {
                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        ConnectedClient connectedClient = new ConnectedClient(socketConnected);
                        ClientConfiguration connectedClientConfiguration = new ClientConfiguration(socketConnected);
                        connectedClient.setClientConfiguration(connectedClientConfiguration);

                        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
                        sessionTracker.addNewConnection(connectedClient);

                        ClientWrappedLoop connectedClientWrappedLoop = new ClientWrappedLoop();

                        try {
                            connectedClient.setupSocket();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SocketExceptions socketExceptions) {
                            socketExceptions.printStackTrace();
                        }
                        connectedClientWrappedLoop.activateWrappedLoop(connectedClient);
                    }
                };
                Thread newClientThread = new Thread(runnable);
                newClientThread.start();
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }

        };

    }
    //Ovdje napraviti novu overload metodu za kreiranje socketa koji ce se povezati na server;
}



