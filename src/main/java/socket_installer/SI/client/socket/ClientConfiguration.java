package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;

import java.net.Socket;

public class ClientConfiguration implements SocketConfiguration {
    private String ipAddress;
    private int port;
    private int timeout;
    private boolean connectedClientStatus;
    private boolean streamPaused;
    private Long threadId;

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
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public int getTimeout() {
        return timeout;
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
        if (streamPaused && connectedClientStatus){
            return false;
        }else{
            return connectedClientStatus;
        }
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    @Override
    public void setSocketOnlineStatus(boolean status) {
        this.connectedClientStatus = status;
    }


    public void setStreamPaused(boolean streamPaused) {
        this.streamPaused = streamPaused;
    }

    public boolean isStreamPaused(){
        return this.streamPaused;
    }
}
