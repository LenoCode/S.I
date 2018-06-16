package socket_installer.SI.server.socket;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;

public class ServerConfiguration implements SocketConfiguration {

    private int backlog;
    private String ipAddress;
    private int port;
    private boolean serverStatus;
    private int timeout;

    public ServerConfiguration(String hostAddress, int port, int backlog,int timeout){
        this.ipAddress = hostAddress;
        this.port = port;
        this.backlog = backlog;
        this.timeout = timeout;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress=ipAddress;
    }
    @Override
    public void setPort(int port) {
        this.port=port;
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
        return serverStatus;
    }
    @Override
    public void setSocketOnlineStatus(boolean status) {
        this.serverStatus = status;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

}
