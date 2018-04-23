package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.interfaces.sockets.SocketConfiguration;

import java.net.Socket;

public class ClientConfiguration implements SocketConfiguration {
    private String ipAddress;
    private int port;
    private boolean connectedClientStatus;
    private boolean streamPaused;

    public ClientConfiguration(Socket socket){
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

    public boolean isStreamPaused() {
        return streamPaused;
    }

    public void setStreamPaused(boolean streamPaused) {
        this.streamPaused = streamPaused;
    }
}
