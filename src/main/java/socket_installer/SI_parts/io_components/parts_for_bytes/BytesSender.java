package socket_installer.SI_parts.io_components.parts_for_bytes;

import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.client.ClientSocket;
import socket_installer.SI_parts.io_components.IO.IOHolder;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class BytesSender {

    private static BytesSender bytesSender;

    private BytesSender(){

    }

    public static BytesSender getBytesSender(){
        if (bytesSender == null){
            bytesSender = new BytesSender();
        }
        return bytesSender;
    }
    public void sendBytes(ClientSocket client, byte[] bytes) throws IOException,SocketExceptions {
        IOHolder ioHolder = client.getIOHolder();
        BufferedOutputStream outputStream = ioHolder.getOutStream();
        outputStream.write(bytes);
    }
}
