package socket_installer.SI_behavior.interfaces.sockets;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface SocketModel {
    void activateSocket() throws IOException,SocketExceptions;
    void deactivateSocket() throws IOException,SocketExceptions;
    void setupSocket() throws IOException,SocketExceptions;
    SocketConfiguration getSocketConfiguration();
}
