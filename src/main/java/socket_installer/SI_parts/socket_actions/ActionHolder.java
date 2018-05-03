package socket_installer.SI_parts.socket_actions;

import socket_installer.SI_parts.socket_actions.recv_response.BufferChecker;
import socket_installer.SI_parts.socket_actions.recv_response.BytesRecvResponder;

public class ActionHolder {

    private final BufferChecker bufferChecker;
    private final BytesRecvResponder bytesResponder;


    public ActionHolder (){
        bufferChecker = new BufferChecker();
        bytesResponder = new BytesRecvResponder();
    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }
    public BytesRecvResponder getBytesResponder(){
        return bytesResponder;
    }
}
