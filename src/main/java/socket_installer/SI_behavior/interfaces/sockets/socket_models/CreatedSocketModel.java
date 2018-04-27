package socket_installer.SI_behavior.interfaces.sockets.socket_models;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface CreatedSocketModel {
    void runSocket() throws IOException, SocketExceptions;
    void closeProgram();
}
