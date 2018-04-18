package S.I_behavior.interfaces.exceptions;

import S.I_behavior.interfaces.sockets.SocketModel;

public interface ExceptionModel {
    void handleException(SocketModel socketModel, Object...args);
}
