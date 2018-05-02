package socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_map;

import socket_installer.SI_behavior.interfaces.socket_actions.recv_response.ProtocolAction;
import socket_installer.SI_parts.protocol.enum_protocol.Protocol;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_actions.CloseSocketAction;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.protocol_actions.NoAction;

import java.util.HashMap;

public class ProtocolMap extends HashMap <String,ProtocolAction> {

    private final NoAction noAction;

    public ProtocolMap(){
        super();
        noAction = new NoAction();
        initActions();
    }
    private void initActions(){
        super.put(Protocol.SOCKET_CLOSED.IdentProtocol(), new CloseSocketAction());
    }

    public ProtocolAction getAction(String key) {
        return super.getOrDefault(key, noAction);
    }
}
