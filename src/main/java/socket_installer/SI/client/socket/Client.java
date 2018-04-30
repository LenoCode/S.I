package socket_installer.SI.client.socket;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;

import java.io.IOException;
import java.net.Socket;

public class Client extends ClientSocket {

    public Client(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        setupIOHolder();
        boolean clientStatus = true;
        //clientConfiguration.setSocketOnlineStatus(clientStatus);
        //Client nije taj koji stalno mora imati ukljucen loop -vazna stavka,,client salje request i prima odgovor, no client objekt sa server strane mora konstatno vrtiti loop, no to mogu napraviti posebno na server objektu
    }

}
