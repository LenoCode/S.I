package socket_installer.SI_behavior.interfaces.sockets.socket_models.client;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface ClientIOLoop {


    void ioLoop() throws IOException,SocketExceptions;
}
