package socket_installer.SI_parts.protocol;


import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.TehnicalProtocol;

public class CommunicationProtocol {

    public byte[] implementSentClientProtocol(String message){
        return String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message).getBytes();
    }
    public byte[] implementClosingSocketProtocol(){
        return TehnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes();
    }
}
