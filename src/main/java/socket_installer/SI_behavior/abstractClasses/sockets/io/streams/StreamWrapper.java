package socket_installer.SI_behavior.abstractClasses.sockets.io.streams;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import java.net.Socket;
import java.net.SocketException;

public abstract class StreamWrapper{
    protected final ClientSocket clientSocket;

    StreamWrapper(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    protected void setInputStreamToBlock() throws SocketException {
        try {
            ((Socket)clientSocket.getSocket()).setSoTimeout(0);
        }catch (SocketException s){
            s.printStackTrace();
        }
    }
    protected void setInputStreamToUnblock() throws SocketException {
        try {
            ((Socket)clientSocket.getSocket()).setSoTimeout(clientSocket.getSocketConfiguration().getTimeout());
        }catch (SocketException s){
            s.printStackTrace();
        }
    }
    protected void setInputStreamToUnblock(int timeout) throws SocketException {
        try {
            ((Socket)clientSocket.getSocket()).setSoTimeout(timeout);
        }catch (SocketException s){
            s.printStackTrace();
        }
    }
}
