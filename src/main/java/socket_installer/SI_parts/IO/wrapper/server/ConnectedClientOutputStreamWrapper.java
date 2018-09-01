package socket_installer.SI_parts.IO.wrapper.server;

import socket_installer.SI_behavior.abstractClasses.sockets.io.streams.OutputStreamWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ConnectedClientOutputStreamWrapper extends OutputStreamWrapper {

    public ConnectedClientOutputStreamWrapper(ClientSocket clientSocket, OutputStream outputStream) {
        super(clientSocket,outputStream);
    }

    @Override
    public void send(byte[] bytes) throws IOException, SocketExceptions {
        try{
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }catch (IOException ioException){
            //Neki specific error, koji ce handlat,da memoriamo poruku koja se nije poslala pa je probati ponovo poslati....
            System.out.println("OVDJE PADNEEEEEEEEEM");
            throw ioException;
        }
    }
}
