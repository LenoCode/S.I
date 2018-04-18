package S.I_behavior.non_abstract_classes.exceptions;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.exceptions.ExceptionModel;
import S.I_behavior.interfaces.sockets.SocketModel;

public class SocketClosedExceptions extends SocketExceptions {


    @Override
    public void handleException(SocketModel socketModel, Object...args) {
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
        System.out.println("Socket is closed");
    }
}
