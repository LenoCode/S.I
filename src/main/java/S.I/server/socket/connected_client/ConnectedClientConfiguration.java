package S.I.server.socket.connected_client;

import S.I_behavior.interfaces.sockets.SocketConfiguration;

import java.net.Socket;

public class ConnectedClientConfiguration implements SocketConfiguration {

    private String ipAddress;
    private int port;
    private boolean connectedClientStatus;

    public ConnectedClientConfiguration(Socket socket){
        ipAddress = socket.getInetAddress().getHostAddress();
        port = socket.getPort();
        connectedClientStatus = true;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public boolean isSocketOnline() {
        return connectedClientStatus;
    }
    @Override
    public void setSocketOnlineStatus(boolean status) {
        this.connectedClientStatus = status;
    }
}
