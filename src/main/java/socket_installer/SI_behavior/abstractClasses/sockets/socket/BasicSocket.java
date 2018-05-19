package socket_installer.SI_behavior.abstractClasses.sockets.socket;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer.Notificationer;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @param <A>
 *     Type of socket (ServerSocket or Socket)
 */

public abstract class BasicSocket<A extends Closeable> implements SocketModel{
    protected A socket;
    protected SocketConfiguration socketConfiguration;
    private Notificationer notificationer;

    @Override
    public void setSocketConfiguration(SocketConfiguration socketConfiguration) {
        this.socketConfiguration = socketConfiguration;
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return socketConfiguration;
    }

    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        if (socket != null){
            socket.close();
            socket = null;
        }
    }


    public boolean equals(String ipAddress) {
        return ipAddress.equals(this.getSocketConfiguration().getIpAddress());
    }

    public Notificationer getNotificationer() {
        return notificationer;
    }

    public void setNotificationer(Notificationer notificationer) {
        this.notificationer = notificationer;
    }
}
