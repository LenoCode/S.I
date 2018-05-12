package socket_installer.SI_parts.socket_actions.recv_response;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.data_carriers.response_carrier.ResponseCarrier;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;
import socket_installer.SI_parts.socket_actions.recv_response.string_parser.StringParser;


import java.io.IOException;

public class BufferChecker {


    public BufferChecker() {

    }

    public void checkStringBuffer(ClientSocket clientSocket) throws IOException,SocketExceptions {
        IOHolder ioHolder = clientSocket.getIOHolder();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();
        ResponseCarrier responseCarrier = ioHolder.getResponseCarrier();

        StringParser.getStringParser().parseForStrings(stringBuffer.getString(),responseCarrier);

    }
}
