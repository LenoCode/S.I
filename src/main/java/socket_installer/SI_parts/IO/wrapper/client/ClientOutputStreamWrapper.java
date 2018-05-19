package socket_installer.SI_parts.IO.wrapper.client;

import socket_installer.SI_parts.exception.client.connection_break_exception.ClientConnectionAbortException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;


import java.io.BufferedOutputStream;
import java.io.IOException;

public class ClientOutputStreamWrapper implements OutputStreamWrapperModel {
    private final BufferedOutputStream bufferedOutputStream;

    public ClientOutputStreamWrapper(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }
    @Override
    public void send(byte[] bytes) throws IOException, SocketExceptions {
        try{
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }catch (IOException ioException){
            System.out.println("Bacam error");
            throw new ClientConnectionAbortException();
        }
    }

    private void waitForByteToBeSend(ClientInputStreamWrapper clientInputStreamWrapper){

    }
}
