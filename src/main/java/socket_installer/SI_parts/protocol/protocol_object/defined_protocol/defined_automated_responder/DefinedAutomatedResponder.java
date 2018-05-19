package socket_installer.SI_parts.protocol.protocol_object.defined_protocol.defined_automated_responder;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.BytesStatusProtocol;

import java.io.IOException;

public class DefinedAutomatedResponder {
    private static DefinedAutomatedResponder definedAutomatedResponder;

    private DefinedAutomatedResponder(){

    }
    public static DefinedAutomatedResponder getDefinedAutomatedResponder(){
        if (definedAutomatedResponder == null){
            definedAutomatedResponder = new DefinedAutomatedResponder();
        }
        return definedAutomatedResponder;
    }

    public void sendBytesSuccessProtocol(IOHolder ioHolder) throws IOException,SocketExceptions {
        ioHolder.getOutputStream().send((BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol()).getBytes());
    }
}
