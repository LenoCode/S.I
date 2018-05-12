package socket_installer.SI_parts.protocol;


import socket_installer.SI_parts.protocol.enum_protocol.ClientProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.TehnicalProtocol;

public class CommunicationProtocol {

    public byte[] implementSentClientProtocol(String message){
        return String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message).getBytes();
    }
    public byte[] implementClosingSocketProtocol(){
        return TehnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes();
    }
}
