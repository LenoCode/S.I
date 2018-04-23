package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.IOHolderModel;
import socket_installer.SI_behavior.interfaces.sockets.SocketConfiguration;
import socket_installer.SI_behavior.interfaces.sockets.client.ClientIOLoop;
import socket_installer.SI_parts.io_components.BytesReader;
import socket_installer.SI_parts.io_components.IOHolder;
import socket_installer.SI_parts.io_components.StringBuffer;

import java.io.IOException;
import java.net.Socket;

public class Client extends BasicSocket {
    private Socket clientSocket;
    private ClientConfiguration clientConfiguration;
    private IOHolder ioHolder;
    private BytesReader bytesReader;
    private ClientIOLoop clientIOLoop;

    public Client(Socket clientSocket,ClientIOLoop clientIOLoop){
        this.clientSocket = clientSocket;
        this.bytesReader = BytesReader.getBytesReader();
        this.clientIOLoop = clientIOLoop;
    }


    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        setupIOHolder();
        boolean clientStatus = true;
        clientConfiguration.setSocketOnlineStatus(clientStatus);
        clientIOLoop.ioLoop();
        //Client nije taj koji stalno mora imati ukljucen loop -vazna stavka,,client salje request i prima odgovor, no client objekt sa server strane mora konstatno vrtiti loop, no to mogu napraviti posebno na server objektu
    }
    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        clientSocket.close();
        clientSocket = null;
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return clientConfiguration;
    }


    public IOHolder getIOHolder() {
        return ioHolder;
    }

    private void setupIOHolder() throws IOException, SocketExceptions{
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
