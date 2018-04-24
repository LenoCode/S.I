package socket_installer.SI_behavior.abstractClasses.sockets.client;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.SocketConfiguration;
import socket_installer.SI_parts.io_components.BytesReader;
import socket_installer.SI_parts.io_components.IOHolder;
import socket_installer.SI_parts.io_components.StringBuffer;

import java.io.IOException;
import java.net.Socket;

public abstract class ClientSocket extends BasicSocket {

    protected Socket clientSocket;
    protected ClientConfiguration clientConfiguration;
    protected IOHolder ioHolder;
    protected BytesReader bytesReader;


    public ClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
        this.bytesReader = BytesReader.getBytesReader();

    }
    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        clientSocket.close();
        clientSocket = null;
    }

    @Override
    public void setupSocket()throws IOException,SocketExceptions{
        setupIOHolder();
        clientConfiguration.setStreamPaused(false);
        clientConfiguration.setSocketOnlineStatus(true);
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return clientConfiguration;
    }


    public IOHolder getIOHolder() {
        return ioHolder;
    }

    protected void setupIOHolder() throws IOException, SocketExceptions{
        ioHolder = new IOHolder();
        ioHolder.setInStream(clientSocket.getInputStream());
        ioHolder.setOutStream(clientSocket.getOutputStream());
        ioHolder.setStringBuffer(new StringBuffer());
    }

    public ClientConfiguration getClientConfiguration() {
        return clientConfiguration;
    }

    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }
}
