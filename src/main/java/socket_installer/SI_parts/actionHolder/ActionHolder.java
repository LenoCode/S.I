package socket_installer.SI_parts.actionHolder;

import socket_installer.SI_parts.actionHolder.actions.buffer_checker.BufferChecker;

public class ActionHolder {

    private final BufferChecker bufferChecker;

    public ActionHolder (){
        bufferChecker = new BufferChecker();

    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }

}
