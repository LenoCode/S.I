package socket_installer.SI_parts.io_components;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;
import socket_installer.SI_parts.exceptions.SocketClosedException;
import socket_installer.SI_parts.exceptions.SocketStreamTimeoutException;


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


    public void readBytes(Client client)throws IOException, SocketExceptions {
        int bytesRead = 0;
        try{
            IOHolder ioHolder = client.getIOHolder();

            byte[] bytes = ioHolder.getBytes();
            StringBuffer buffer = ioHolder.getStringBuffer();
            BufferedInputStream inputStream = ioHolder.getInStream();

            bytesRead = inputStream.read(bytes);
            buffer.insertToBuffer(bytesRead,bytes);

        } catch (SocketTimeoutException socketTimeoutException){

        } catch (IOException ioException){
            switch (bytesRead){
                case -1:
                    throw new SocketClosedException();
                default:
                    throw new IOException(ioException);
            }
        }
    }

}
