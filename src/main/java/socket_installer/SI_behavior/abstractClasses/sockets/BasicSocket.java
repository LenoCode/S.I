package socket_installer.SI_behavior.abstractClasses.sockets;

import socket_installer.SI_behavior.interfaces.io_observer.notification_handler.NotificationHandler;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public abstract class BasicSocket implements SocketModel{
    protected NotificationHandler notificationHandler;

    public void setNotificationHandler(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    @Override
    public boolean equals(Object obj) {
        BasicSocket socket = (BasicSocket)obj;
        String ipAddress = socket.getSocketConfiguration().getIpAddress();
        return ipAddress.equals(this.getSocketConfiguration().getIpAddress());
    }
}
