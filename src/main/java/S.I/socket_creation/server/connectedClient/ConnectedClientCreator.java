package S.I.socket_creation.server.connectedClient;

import S.I.server.socket.connected_client.ConnectedClient;
import S.I.server.socket.connected_client.ConnectedClientConfiguration;
import S.I.server.socket_actions.server_loop.ConnectedClientWrappedLoop;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.session_tracker.server.SessionTracker;

import java.net.Socket;

public class ConnectedClientCreator {

    public static CreatedSocketModel createConnectedClient(Socket socketConnected, SessionTracker sessionTracker){
        return new CreatedSocketModel() {
            @Override
            public void runSocket() {
                Runnable runnable = new Runnable() {

                    @Override
                    public void run() {
                        ConnectedClient connectedClient = new ConnectedClient(socketConnected);
                        ConnectedClientConfiguration connectedClientConfiguration = new ConnectedClientConfiguration(socketConnected);
                        connectedClient.setConnectedClientConfiguration(connectedClientConfiguration);
                        sessionTracker.addNewConnection(connectedClient);

                        ConnectedClientWrappedLoop connectedClientWrappedLoop = new ConnectedClientWrappedLoop();
                        connectedClientWrappedLoop.setSessionTracker(sessionTracker);
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



