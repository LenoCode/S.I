package socket_installer.SI_parts.socket_actions.recv_response;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer.StringBuffer;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_parser.StringParser;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.ProtocolCheck;

import java.io.IOException;
import java.util.Iterator;

public class BufferChecker {


    private final ProtocolCheck protocolCheck;

    public BufferChecker() {
        protocolCheck = new ProtocolCheck();
    }


    public void checkStringBuffer(StringBuffer stringBuffer, BasicSocket basicSocket) throws IOException,SocketExceptions {
        Iterator<String> stringIterator = StringParser.getStringParser().parseForStrings(stringBuffer.getString());
        if (stringIterator != null) {
            while (stringIterator.hasNext()) {
                String nextString = stringIterator.next();
                if (!protocolCheck.checkProtocol(nextString, basicSocket)) {
                    basicSocket.getNotificationer().notificationRecv(nextString);
                }
            }
        }
    }
}
