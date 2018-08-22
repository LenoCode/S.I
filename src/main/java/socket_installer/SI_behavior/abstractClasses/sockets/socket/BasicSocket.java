package socket_installer.SI_behavior.abstractClasses.sockets.socket;

import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.Closeable;

/**
 *
 * @param <A>
 *     Type of socket (ServerSocket or Socket)
 */

public abstract class BasicSocket<A extends Closeable> implements SocketModel{
    protected A socket;
    protected SocketConfiguration socketConfiguration;

    @Override
    public void setSocketConfiguration(SocketConfiguration socketConfiguration) {
        this.socketConfiguration = socketConfiguration;
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return socketConfiguration;
    }



    public boolean equals(String ipAddress) {
        return ipAddress.equals(this.getSocketConfiguration().getIpAddress());
    }

    public A getSocket() {
        return socket;
    }
}
