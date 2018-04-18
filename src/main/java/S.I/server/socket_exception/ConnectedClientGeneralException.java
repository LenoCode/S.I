package S.I.server.socket_exception;

import S.I_behavior.interfaces.sockets.SocketModel;

public class ConnectedClientGeneralException {

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        System.out.println(exception.getMessage());
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
        return false;
    }
}
