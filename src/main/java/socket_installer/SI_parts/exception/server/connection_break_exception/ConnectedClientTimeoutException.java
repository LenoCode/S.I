package socket_installer.SI_parts.exception.server.connection_break_exception;


import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.TehnicalProtocol;

import java.io.IOException;


public class ConnectedClientTimeoutException extends SocketExceptions {

    @Override
    public void handleException(SocketModel socketModel) {
        ClientSocket clientSocket = (ClientSocket) socketModel;
        streamPaused((ClientConfiguration) clientSocket.getSocketConfiguration());
        notifyClientAboutTimeout(clientSocket);
        closeSocket(socketModel);
    }

    private void streamPaused(ClientConfiguration clientConfiguration){
        clientConfiguration.setStreamPaused(true);
    }


    private void notifyClientAboutTimeout(ClientSocket clientSocket){
        try{
            System.out.println("Sending closed ");
            OutputStreamWrapperModel outputStreamWrapper = clientSocket.getIOHolder().getOutputStream();
            outputStreamWrapper.send(TehnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes());
            Thread.sleep(50000);
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void closeSocket (SocketModel socketModel){
        try {
            System.out.println("DEACTIVED");
            socketModel.deactivateSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }
}
