package socket_installer.SI_parts.io_components;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;


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
            throw new SocketExceptions() {
                @Override
                public void handleException(SocketModel socketModel) {
                    ((ClientConfiguration)client.getClientConfiguration()).setSocketOnlineStatus(false);
                }
            };
        } catch (IOException ioException){
            switch (bytesRead){
                case -1:
                    throw new IOException("CLOSED");
                default:
                    throw new IOException(ioException);
            }
        }
    }

}
