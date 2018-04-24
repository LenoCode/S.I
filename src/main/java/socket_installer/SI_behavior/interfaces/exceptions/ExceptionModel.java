package socket_installer.SI_behavior.interfaces.exceptions;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public interface ExceptionModel {
    void handleException(SocketModel socketModel);
}
