package socket_installer.SI_parts.socket_actions;

import socket_installer.SI_parts.protocol.CommunicationProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.BufferChecker;
import socket_installer.SI_parts.socket_actions.recv_response.BytesRecvResponder;

public class ActionHolder {

    private final BufferChecker bufferChecker;
    private final BytesRecvResponder bytesResponder;
    private final CommunicationProtocol communicationProtocol;


    public ActionHolder (){
        bufferChecker = new BufferChecker();
        bytesResponder = new BytesRecvResponder();
        communicationProtocol = new CommunicationProtocol();

    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }
    public BytesRecvResponder getBytesResponder(){
        return bytesResponder;
    }
    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

}
