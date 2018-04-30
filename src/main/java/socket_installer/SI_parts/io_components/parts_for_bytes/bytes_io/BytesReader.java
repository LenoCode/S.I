package socket_installer.SI_parts.io_components.parts_for_bytes.bytes_io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import socket_installer.SI.client.socket_exception.specific_exceptions.connection_break_exception.ClientTimeoutException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
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
        IOHolder ioHolder = client.getIOHolder();
        byte[] bytes = ioHolder.getBytes();
        StringBuffer buffer = ioHolder.getStringBuffer();
        BufferedInputStream inputStream = ioHolder.getInStream();

        read(inputStream,buffer,bytes);
    }

    private void read(BufferedInputStream inputStream, StringBuffer buffer,byte[] bytes) throws IOException, SocketExceptions{
        try{
            int bytesRead = 0;

            bytesRead = inputStream.read(bytes);
            buffer.insertToBuffer(bytesRead,bytes);
        }
        catch (SocketTimeoutException socketTimeoutException){
            throw new ClientTimeoutException();
        }catch (IOException ioException){
            
        }
    }
}
