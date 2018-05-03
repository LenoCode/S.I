package socket_installer.SI_parts.io_components.IO.wrapper;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class OutputStreamWrapper {
    private final BufferedOutputStream bufferedOutputStream;

    public OutputStreamWrapper(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }

    public void send(byte[] bytes) throws IOException, SocketExceptions {
        try{
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            System.out.println("Sent the files");
        }catch (IOException ioException){
            //Neki specific error, koji ce handlat,da memoriamo poruku koja se nije poslala pa je probati ponovo poslati....
            ioException.printStackTrace();
        }
    }
}
