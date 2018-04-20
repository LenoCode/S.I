package socket_installer.SI_behavior.interfaces.sockets;

public interface SocketConfiguration {

    void setSocketOnlineStatus(boolean status);
    void setIpAddress(String ipAddress);
    void setPort(int port);
    String getIpAddress();
    int getPort();
    boolean isSocketOnline();

}
