package socket_installer.SI_parts.socket_actions;

import socket_installer.SI_parts.socket_actions.recv_response.BufferChecker;

public class ActionHolder {

    private final BufferChecker bufferChecker;

    public ActionHolder (){
        bufferChecker = new BufferChecker();
    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }
}
