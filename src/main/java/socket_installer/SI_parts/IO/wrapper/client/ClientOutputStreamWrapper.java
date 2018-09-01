package socket_installer.SI_parts.IO.wrapper.client;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.io.streams.OutputStreamWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientConnectionAbortException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLOutput;

public class ClientOutputStreamWrapper extends OutputStreamWrapper {

    public ClientOutputStreamWrapper(ClientSocket clientSocket, OutputStream outputStream) {
        super(clientSocket,outputStream);
    }
    @Override
    public void send(byte[] bytes) throws IOException, SocketExceptions {
        try{
            System.out.println("SALJEM DATA");
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }catch (IOException socketExceptions){
            System.out.println("Tu sam pao");
            bringOutputStreamUp(bytes);
        }
        finally {
            setInputStreamToBlock();
        }
    }

    private void bringOutputStreamUp(byte[] bytes) throws ClientConnectionAbortException {
        boolean streamUp = false;
        int tryCounter = 0;

        while(!streamUp){
            try {
                System.out.println("BRINGING STREAM UPPPPPPP");
                ((Client)clientSocket).reconnectSocket();
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                streamUp = true;
                System.out.println("DATA SENT");
            }catch (IOException ioException){
                ioException.printStackTrace();
                throw new ClientConnectionAbortException();
            }catch (SocketExceptions socketExceptions) {
                socketExceptions.printStackTrace();
                throw new ClientConnectionAbortException();
            }
        }
    }

}
