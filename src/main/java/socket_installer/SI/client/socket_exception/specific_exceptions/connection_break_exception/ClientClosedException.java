package socket_installer.SI.client.socket_exception.specific_exceptions.connection_break_exception;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public class ClientClosedException extends SocketExceptions {
    @Override
    public void handleException(SocketModel socketModel) {
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
    }
}
