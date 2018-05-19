package socket_installer.SI.socket_creation.server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI.server.socket_actions.connection_handler.NewConnectionHandler;
import socket_installer.SI.server.socket_actions.socket_loop.ServerWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer.Notificationer;

import socket_installer.SI_parts.context.ContextObject;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;
import socket_installer.SI_context.internal_context.InternalContext;

import java.io.IOException;


public class ServerCreator {

    private ServerCreator(){
    }
    public static ServerCreatedSocket createServer(
            String hostAddress,
            Notificationer notificationer,
            int port,
            int backlog,
            int timeout
    ){
        return new ServerCreatedSocket() {
            @Override
            public void runSocket() throws IOException, SocketExceptions {
                InternalContext.createContext();
                ProgramLoopWrapper.setProgrammRunning(true);

                ServerConfiguration serverConfiguration = new ServerConfiguration(hostAddress,port,backlog,timeout);
                ContextObject<SessionTracker> sessionTrackerContextObject = new ContextObject<>();
                sessionTrackerContextObject.setContextObject(new SessionTracker());

                InternalContext.getInternalContext().saveContextObject(sessionTrackerContextObject);

                basicSocket = new Server(new NewConnectionHandler());
                basicSocket.setSocketConfiguration(serverConfiguration);
                basicSocket.setNotificationer(notificationer);

                ServerWrappedLoop serverWrappedLoop = new ServerWrappedLoop();
                basicSocket.setupSocket();
                serverWrappedLoop.activateWrappedLoop(basicSocket);
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }
        };
    }
}
