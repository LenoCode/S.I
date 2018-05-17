package socket_installer.SI_parts.exception.client.general;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public class ClientGeneralException {

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
        return false;
    }
}
