package socket_installer.SI.server.socket_exception.specific_exceptions.connection_break_exception;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;

public class ConnectedClientClosed extends SocketExceptions {

    @Override
    public void handleException(SocketModel socketModel) {
        try {
            SessionTracker sessionTracker = (SessionTracker)InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
            socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
            socketModel.deactivateSocket();
            sessionTracker.removeConnection(socketModel);
            System.out.println("Connected Client closed");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }
}
