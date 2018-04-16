package S.I_behavior.interfaces.sockets;

import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;

import java.io.IOException;

public interface SocketModel {
    void activateSocket() throws IOException, AbstractSpecificException;
    void deactivateSocket() throws IOException, AbstractSpecificException;
    SocketConfiguration getSocketConfiguration();
}
