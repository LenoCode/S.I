package socket_installer.SI_parts.io_components.parts_for_bytes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import socket_installer.SI.client.socket_exception.specific_exceptions.ClientClosedException;
import socket_installer.SI.client.socket_exception.specific_exceptions.ClientTimeoutException;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;
import socket_installer.SI_parts.io_components.IO.IOHolder;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer.StringBuffer;


public class BytesReader {

    private static BytesReader bytesReader;

    private BytesReader(){
    }

    public static BytesReader getBytesReader(){
        if (bytesReader == null){
            bytesReader = new BytesReader();
        }
        return bytesReader;
    }

    public void readBytes(ClientSocket client)throws IOException, SocketExceptions {
        int bytesRead = 0;
        try{
            IOHolder ioHolder = client.getIOHolder();

            byte[] bytes = ioHolder.getBytes();
            StringBuffer buffer = ioHolder.getStringBuffer();
            BufferedInputStream inputStream = ioHolder.getInStream();

            bytesRead = inputStream.read(bytes);
            buffer.insertToBuffer(bytesRead,bytes);

        } catch (SocketTimeoutException socketTimeoutException){
            throw new ClientTimeoutException();
        } catch (IOException ioException){
            switch (bytesRead){
                case -1:
                    throw new ClientClosedException();
                default:
                    throw new IOException(ioException);
            }
        }
    }
}
