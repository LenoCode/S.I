package socket_installer.SI_parts.io_components.IO.wrapper;

import socket_installer.SI.client.socket_exception.specific_exceptions.connection_break_exception.ClientClosedException;
import socket_installer.SI.client.socket_exception.specific_exceptions.connection_break_exception.ClientTimeoutException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class InputStreamWrapper {

    private final BufferedInputStream bufferedInputStream;

    public InputStreamWrapper(BufferedInputStream bufferedInputStream){
        this.bufferedInputStream = bufferedInputStream;
    }

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
            throw new ClientTimeoutException();
        }catch (IOException ioException){
            throw new ClientClosedException();
        }
    }
}
