package S.I_behavior.non_abstract_classes.exceptions;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.session_tracker.server.SessionTracker;

public class SocketStreamTimeoutException extends SocketExceptions {


    @Override
    public void handleException(SocketModel socketModel, Object... args) {
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
    }
}
