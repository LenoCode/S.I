package socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_actions;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.socket_actions.recv_response.ProtocolAction;

public class NoAction implements ProtocolAction {
    @Override
    public boolean action(String notification, ClientSocket socket) throws SocketExceptions {
        return false;
    }
}
