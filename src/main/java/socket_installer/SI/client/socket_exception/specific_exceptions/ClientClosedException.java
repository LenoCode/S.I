package socket_installer.SI.client.socket_exception.specific_exceptions;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

public class ClientClosedException extends SocketExceptions {
    @Override
    public void handleException(SocketModel socketModel) {
        System.out.println("Closing");
        socketModel.getSocketConfiguration().setSocketOnlineStatus(false);
    }
}
