package socket_installer.SI_behavior.interfaces.wrapped_loop.loop;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

public interface WrappedLoopModel {
    default void activateWrappedLoop(SocketModel socketModel)throws NoSolutionForException{}
    default void activateWrappedLoop(SocketModel socketModel,String classIdent,String methodIdent,String notification)throws NoSolutionForException{}
}
