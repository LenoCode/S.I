package socket_installer.SI.server.socket_exception;

import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

public class ConnectedClientGeneralException {

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        System.out.println(exception.getMessage());
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
        return false;
    }
}
