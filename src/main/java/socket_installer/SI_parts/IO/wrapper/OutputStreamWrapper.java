package socket_installer.SI_parts.IO.wrapper;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class OutputStreamWrapper {
    private final BufferedOutputStream bufferedOutputStream;

    public OutputStreamWrapper(BufferedOutputStream bufferedOutputStream) {
        this.bufferedOutputStream = bufferedOutputStream;
    }
    //OVO MOZDA MOGU BOLJE NAPRAVITI DA NE MORAM U OSTALIM KLASAMA OVU METODU OVERRIDAT, NAPRAVITI INTERFACE
    public void send(byte[] bytes) throws IOException, SocketExceptions {
        try{
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }catch (IOException ioException){
            //Neki specific error, koji ce handlat,da memoriamo poruku koja se nije poslala pa je probati ponovo poslati....
            ioException.printStackTrace();
        }
    }
}
