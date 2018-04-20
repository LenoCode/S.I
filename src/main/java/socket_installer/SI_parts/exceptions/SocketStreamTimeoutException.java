package socket_installer.SI_parts.exceptions;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

public class SocketStreamTimeoutException extends SocketExceptions {


    @Override
    public void handleException(SocketModel socketModel, Object... args) {
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
    }
}
