package socket_installer.SI_behavior.interfaces.sockets.socket_models;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;

import java.io.IOException;

public interface SocketModel {
    void activateSocket() throws IOException,SocketExceptions;
    void deactivateSocket() throws IOException,SocketExceptions;
    void setupSocket() throws IOException,SocketExceptions;
    SocketConfiguration getSocketConfiguration();
}
