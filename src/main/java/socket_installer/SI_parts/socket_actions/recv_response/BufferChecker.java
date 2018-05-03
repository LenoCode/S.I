package socket_installer.SI_parts.socket_actions.recv_response;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;
import socket_installer.SI_parts.socket_actions.recv_response.string_parser.StringParser;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.ProtocolCheck;

import java.io.IOException;
import java.util.Iterator;

public class BufferChecker {

    private final ProtocolCheck protocolCheck;

    public BufferChecker() {
        protocolCheck = new ProtocolCheck();
    }


    public boolean checkStringBuffer(ClientSocket clientSocket) throws IOException,SocketExceptions {
        IOHolder ioHolder = clientSocket.getIOHolder();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();
        Iterator<String> stringIterator = StringParser.getStringParser().parseForStrings(stringBuffer.getString());

        if (stringIterator != null) {
            while (stringIterator.hasNext()) {
                String nextString = stringIterator.next();
                System.out.println(nextString);
                if (!protocolCheck.checkProtocol(nextString, clientSocket)) {
                    clientSocket.getNotificationer().notificationRecv(nextString);
                }
            }
            return true;
        }
        return false;
    }
}
