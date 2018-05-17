package socket_installer.SI_behavior.interfaces.user_implementation.io_notification;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface NotificationerModel {
    void notificationRecv(String notification) throws IOException, SocketExceptions;
}
