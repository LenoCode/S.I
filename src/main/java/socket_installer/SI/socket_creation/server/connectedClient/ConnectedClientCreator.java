package socket_installer.SI.socket_creation.server.connectedClient;

import socket_installer.SI.server.socket.connected_client.ConnectedClient;
import socket_installer.SI.server.socket.connected_client.ConnectedClientConfiguration;
import socket_installer.SI.server.socket_actions.server_loop.ConnectedClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.interfaces.sockets.CreatedSocketModel;

import java.net.Socket;

public class ConnectedClientCreator {

    public static CreatedSocketModel createConnectedClient(Socket socketConnected){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() {
                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        ConnectedClient connectedClient = new ConnectedClient(socketConnected);
                        ConnectedClientConfiguration connectedClientConfiguration = new ConnectedClientConfiguration(socketConnected);
                        connectedClient.setConnectedClientConfiguration(connectedClientConfiguration);

                        ConnectedClientWrappedLoop connectedClientWrappedLoop = new ConnectedClientWrappedLoop();
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
}



