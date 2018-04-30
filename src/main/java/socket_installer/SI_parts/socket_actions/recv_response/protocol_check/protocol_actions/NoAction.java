package socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_actions;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.socket_actions.recv_response.ProtocolAction;

public class NoAction implements ProtocolAction {
    @Override
    public boolean action(String notification, BasicSocket socket) {
        return false;
    }
}
