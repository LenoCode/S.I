package socket_installer.SI_parts.io_components.IO.wrapper.server;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class ConnectedClientOutputStreamWrapper implements OutputStreamWrapperModel {
    private final BufferedOutputStream bufferedOutputStream;

    public ConnectedClientOutputStreamWrapper(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }

    public void send(byte[] bytes) throws IOException, SocketExceptions {
        System.out.println("Sending files");
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
