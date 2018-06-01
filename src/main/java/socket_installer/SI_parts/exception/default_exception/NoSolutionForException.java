package socket_installer.SI_parts.exception.default_exception;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public class NoSolutionForException extends SocketExceptions {
    //Exception kad nemamo rješenje za dani problem
    public NoSolutionForException(String string){
        super(string);
    }
    @Override
    public void handleException(SocketModel socketModel) {
        System.exit(0);
    }
}
