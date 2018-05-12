package socket_installer.SI_behavior.interfaces.sockets.io_models;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;
import java.net.Socket;

public interface IOHolderSetupMethodModel {
    void setupIOHolder()throws IOException, SocketExceptions;
    void replaceSocket(Socket socket) throws IOException, SocketExceptions;
}
