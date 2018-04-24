package socket_installer.SI_parts.protocol;

import socket_installer.SI_parts.protocol.enum_protocol.Protocol;

public class BasicSocketCommunicationProtocol {

    public byte[] implementSentProtocol(String message){
        return String.format(Protocol.SEND_MESSAGE_PROTOCOL.getProtocol(),message).getBytes();
    }
}
