package socket_installer.SI_parts.protocol;

import socket_installer.SI_parts.protocol.enum_protocol.BytesStatusProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.TehnicalProtocol;

public class CommunicationProtocol {

    public byte[] implementSentProtocol(String message){
        return String.format(BytesStatusProtocol.SEND_BYTES.completeProtocol(),message).getBytes();
    }
    public byte[] implementClosingSocketProtocol(){
        return TehnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes();
    }
}
