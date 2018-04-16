package S.I_behavior.interfaces.exceptions;

import S.I_behavior.interfaces.sockets.SocketModel;

public interface GeneralExceptionModel {
    boolean handleGeneralException(Exception exception, SocketModel socketModel);
}
