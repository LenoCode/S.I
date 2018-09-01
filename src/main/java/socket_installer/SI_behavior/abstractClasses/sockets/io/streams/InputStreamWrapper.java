package socket_installer.SI_behavior.abstractClasses.sockets.io.streams;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public abstract class InputStreamWrapper extends StreamWrapper implements InputStreamWrapperModel {
    protected PushbackInputStream pushbackInputStream;
    protected InputStreamWrapper(ClientSocket clientSocket, InputStream inputStream) {
        super(clientSocket);
        pushbackInputStream = new PushbackInputStream(inputStream);
    }

    public void replaceInputStream(InputStream inputStream){
        this.pushbackInputStream = new PushbackInputStream(inputStream);
    }

    @Override
    public boolean dataAvailable() throws IOException, SocketExceptions {
        setInputStreamToUnblock();
        int bytesRead;
        boolean status;
        if ((bytesRead = pushbackInputStream.read()) == -1){
            status = false;
        }else{
            pushbackInputStream.unread(bytesRead);
            status = true;
        }
        setInputStreamToBlock();
        return status;
    }
}
