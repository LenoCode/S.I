package socket_installer.SI_behavior.interfaces.wrapped_loop.loop;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

public interface WrappedLoopModel {
    void activateWrappedLoop(SocketModel socketModel)throws NoSolutionForException;
}
