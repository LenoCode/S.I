package socket_installer.SI.socket_creation.server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI.server.socket_actions.connection_handler.NewConnectionHandler;
import socket_installer.SI.server.socket_actions.socket_loop.ServerWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;

import socket_installer.SI_behavior.interfaces.notification.ServerNotificationerImplModel;
import socket_installer.SI_context.context_object.ContextObject;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;
import socket_installer.SI_context.internal_context.InternalContext;

import java.io.IOException;


public class ServerSocketCreator {

    private ServerSocketCreator(){
    }
    public static ServerCreatedSocket createServer(
            String hostAddress,
            ServerNotificationerImplModel serverNotificationerImplModel,
            int port,
            int backlog,
            int timeout
    ){
        return new ServerCreatedSocket() {
            @Override
            public void initSocket() throws IOException, SocketExceptions {
                InternalContext.createContext();
                ProgramLoopWrapper.setProgrammRunning(true);

                ServerConfiguration serverConfiguration = new ServerConfiguration(hostAddress,port,backlog,timeout);
                ContextObject<SessionTracker> sessionTrackerContextObject = new ContextObject<>();
                sessionTrackerContextObject.setContextObject(new SessionTracker());

                InternalContext.getInternalContext().saveContextObject(sessionTrackerContextObject);

                basicSocket = new Server(new NewConnectionHandler());
                basicSocket.setSocketConfiguration(serverConfiguration);
                basicSocket.setNotificationerImplModel(serverNotificationerImplModel);

                basicSocket.setupSocket();
            }

            @Override
            public void runSocket() throws IOException, SocketExceptions {
                ServerWrappedLoop serverWrappedLoop = new ServerWrappedLoop();
                serverWrappedLoop.activateWrappedLoop(basicSocket);
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
