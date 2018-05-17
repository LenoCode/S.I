package general;


import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.BytesStatusProtocol;
import socket_installer.SI_parts.actionHolder.actions.buffer_checker.BufferChecker;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;

import java.io.IOException;

public class Main {


    public static void main(String[] args){
        StringBuffer stringBuffer = new StringBuffer();
        BufferChecker bufferChecker = new BufferChecker();
        String k = BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol()+"123456789";

        stringBuffer.insertToBuffer(k.length(),k.getBytes());
        try {
            System.out.println(bufferChecker.checkStringBuffer(stringBuffer));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
        k = " novi string";
        stringBuffer.insertToBuffer(k.length(), k.getBytes());
        System.out.println(stringBuffer.getString());

    }
}
