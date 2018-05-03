package socket_installer.SI_parts.socket_actions.recv_response.protocol_check;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.socket_actions.recv_response.ProtocolAction;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_map.ProtocolMap;

import java.io.IOException;

public class ProtocolCheck {

    private final ProtocolMap protocolMap;

    public ProtocolCheck(){
        protocolMap = new ProtocolMap();
    }

    public boolean checkProtocol(String notification, ClientSocket clientSocket) throws IOException,SocketExceptions {
        ProtocolAction protocolAction = protocolMap.getAction(notification);
        return protocolAction.action(notification,clientSocket);
    }
}
