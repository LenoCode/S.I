package socket_installer.SI_parts.IO.wrapper.client;

import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class ClientInputStreamWrapper implements InputStreamWrapperModel {

    private final BufferedInputStream bufferedInputStream;

    public ClientInputStreamWrapper(BufferedInputStream bufferedInputStream){
        this.bufferedInputStream = bufferedInputStream;
    }

    @Override
    public void read(byte[] bytes, StringBuffer buffer) throws IOException,SocketExceptions {
        int bytesRead = 0;
        try{
            bytesRead = bufferedInputStream.read(bytes);

            if (bytesRead == -1){
                throw new ClientClosedException();
            }
            buffer.insertToBuffer(bytesRead,bytes);
        }
        catch (SocketTimeoutException socketTimeoutException){
            throw new ConnectedClientTimeoutException();
        }catch (IOException ioException){
            System.out.println("Bacam exception");
            throw new ClientClosedException();
        }
    }
}
