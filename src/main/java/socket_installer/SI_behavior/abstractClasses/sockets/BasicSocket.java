package socket_installer.SI_behavior.abstractClasses.sockets;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

public abstract class BasicSocket implements SocketModel {


    @Override
    public boolean equals(Object obj) {
        BasicSocket socket = (BasicSocket)obj;
        String ipAddress = socket.getSocketConfiguration().getIpAddress();
        return ipAddress.equals(this.getSocketConfiguration().getIpAddress());
    }
}
