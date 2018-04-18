package S.I.server.socket.connected_client;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.io_components.BytesReader;
import S.I_behavior.non_abstract_classes.io_components.IOHolder;
import S.I_behavior.non_abstract_classes.io_components.StringBuffer;

import java.io.IOException;
import java.net.Socket;

public class ConnectedClient implements SocketModel {

    private Socket clientSocket;
    private ConnectedClientConfiguration connectedClientConfiguration;
    private IOHolder ioHolder;
    private BytesReader bytesReader;

    public ConnectedClient(Socket clientSocket){
        this.clientSocket = clientSocket;
        this.bytesReader = BytesReader.getBytesReader();
    }

    public void setConnectedClientConfiguration(ConnectedClientConfiguration connectedClientConfiguration) {
        this.connectedClientConfiguration = connectedClientConfiguration;
    }

    public void setBytesReader(BytesReader bytesReader) {
        this.bytesReader = bytesReader;
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        setupIOHolder();
        boolean clientStatus = true;
        connectedClientConfiguration.setSocketOnlineStatus(clientStatus);

        while(clientStatus){
            bytesReader.readBytes(ioHolder);
        }
    }
    @Override
    public void deactivateSocket() throws IOException, SocketExceptions {
        clientSocket.close();
        clientSocket = null;
    }

    @Override
    public SocketConfiguration getSocketConfiguration() {
        return connectedClientConfiguration;
    }

    private void setupIOHolder() throws IOException, SocketExceptions{
        ioHolder = new IOHolder();
        ioHolder.setInStream(clientSocket.getInputStream());
        ioHolder.setOutStream(clientSocket.getOutputStream());
        ioHolder.setStringBuffer(new StringBuffer());
    }

}
