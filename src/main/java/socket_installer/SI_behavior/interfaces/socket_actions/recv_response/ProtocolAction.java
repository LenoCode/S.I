package socket_installer.SI_behavior.interfaces.socket_actions.recv_response;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface ProtocolAction {

    boolean action(String notification, ClientSocket socket) throws IOException,SocketExceptions;
}