package socket_installer.SI_behavior.abstractClasses.sockets;

import socket_installer.SI_behavior.interfaces.io_observer.notification_handler.NotificationHandler;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;


public abstract class CreatedSocket <A extends BasicSocket> implements CreatedSocketModel {
    protected A basicSocket;

}
