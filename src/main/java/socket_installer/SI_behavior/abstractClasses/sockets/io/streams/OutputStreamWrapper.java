package socket_installer.SI_behavior.abstractClasses.sockets.io.streams;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

public abstract class OutputStreamWrapper extends StreamWrapper implements OutputStreamWrapperModel {
    protected BufferedOutputStream bufferedOutputStream;

    protected OutputStreamWrapper(ClientSocket clientSocket, OutputStream outputStream) {
        super(clientSocket);
        bufferedOutputStream = new BufferedOutputStream(outputStream);
    }

    public void replaceOutputStream(OutputStream outputStream){
        this.bufferedOutputStream = new BufferedOutputStream(outputStream);
    }
}
