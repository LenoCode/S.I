package socket_installer.SI_parts.protocol;

import socket_installer.SI_parts.protocol.enum_protocol.Protocol;

public class CommunicationProtocol {

    public byte[] implementSentProtocol(String message){
        return String.format(Protocol.SEND_STRING_PROTOCOL.completeProtocol(),message).getBytes();
    }
    public byte[] implementClosingSocketProtocol(){
        return Protocol.SOCKET_CLOSED.completeProtocol().getBytes();
    }
}
