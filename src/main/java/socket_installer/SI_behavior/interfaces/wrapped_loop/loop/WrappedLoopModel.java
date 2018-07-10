package socket_installer.SI_behavior.interfaces.wrapped_loop.loop;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

import java.io.IOException;

public interface WrappedLoopModel {
    default void activateWrappedLoop(SocketModel socketModel)throws NoSolutionForException{}
    default void activateWrappedLoop(SocketModel socketModel,String classIdent,String methodIdent,String notification)throws IOException,SocketExceptions {}
}
