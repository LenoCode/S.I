package socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions;

import socket_installer.SI_behavior.interfaces.exceptions.ExceptionModel;

public abstract class SocketExceptions extends Exception implements ExceptionModel {


    public SocketExceptions(String string) {
        super(string);
    }
    public SocketExceptions(){
        super();
    }
}
