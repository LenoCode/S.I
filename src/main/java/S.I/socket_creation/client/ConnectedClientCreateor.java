package S.I.socket_creation.client;

import S.I.server.socket.ServerConfiguration;
import S.I.server.socket.connected_client.ConnectedClient;
import S.I.server.socket.connected_client.ConnectedClientConfiguration;
import S.I.server.socket_actions.server_loop.ConnectedClientWrappedLoop;
import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ErrorWrappedLoop;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClientCreateor {

    public static CreatedSocketModel createConnectedClient(Socket socketConnected){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ConnectedClient connectedClient = new ConnectedClient(socketConnected);
                        ConnectedClientConfiguration connectedClientConfiguration = new ConnectedClientConfiguration();
                        connectedClientConfiguration.setIpAddress(socketConnected.getInetAddress().getHostAddress());
                        connectedClientConfiguration.setPort(socketConnected.getPort());

                        ConnectedClientWrappedLoop connectedClientWrappedLoop = new ConnectedClientWrappedLoop();
                        connectedClientWrappedLoop.activateWrappedLoop(connectedClient);
                    }
                };
            }

            @Override
            public void closeProgram() {
                ErrorWrappedLoop.setProgrammRunning(false);
            }
        };
    }
}
