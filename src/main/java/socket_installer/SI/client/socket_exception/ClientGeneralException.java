package socket_installer.SI.client.socket_exception;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public class ClientGeneralException {

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        System.out.println(exception.getMessage());
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
        return false;
    }
}
