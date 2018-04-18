package S.I_behavior.interfaces.sockets;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;
import java.net.SocketTimeoutException;

public interface SocketModel {
    void activateSocket() throws IOException,SocketExceptions;
    void deactivateSocket() throws IOException,SocketExceptions;
    SocketConfiguration getSocketConfiguration();
}
