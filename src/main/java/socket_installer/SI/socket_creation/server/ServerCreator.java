package socket_installer.SI.socket_creation.server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI.server.socket_actions.connection_handler.NewConnectionHandler;
import socket_installer.SI.server.socket_actions.socket_loop.ServerWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.CreatedSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;
import socket_installer.SI_parts.context.ContextObject;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;
import socket_installer.SI_context.internal_context.InternalContext;

import java.io.IOException;

public class ServerCreator {

    private ServerCreator(){
    }
    public static CreatedSocket createServer(
            String hostAddress,
            int port,
            int backlog,
            int timeout
    ){
        return new CreatedSocket() {
            @Override
            public void runSocket(){
                InternalContext.createContext();
                ProgramLoopWrapper.setProgrammRunning(true);

                ServerConfiguration serverConfiguration = new ServerConfiguration(hostAddress,port,backlog,timeout);
                ContextObject<SessionTracker> sessionTrackerContextObject = new ContextObject<>();
                sessionTrackerContextObject.setContextObject(new SessionTracker());
                InternalContext.getInternalContext().saveContextObject(sessionTrackerContextObject);

                basicSocket = new Server(serverConfiguration,new NewConnectionHandler());

                ServerWrappedLoop serverWrappedLoop = new ServerWrappedLoop();
                try {
                    ((Server)basicSocket).setupSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverWrappedLoop.activateWrappedLoop(server);
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }
        };
    }

    public static CreatedSocket createServer(){
        return new CreatedSocket() {
            @Override
            public void runSocket() throws IOException {

            }

            @Override
            public void closeProgram() {

            }
        };
    }
}
