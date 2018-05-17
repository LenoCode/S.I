package socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface OutputStreamWrapperModel {
    void send(byte[] bytes)throws IOException, SocketExceptions;
}
