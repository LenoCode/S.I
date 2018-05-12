package socket_installer.SI_parts.socket_actions.recv_response;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.protocol.enum_protocol.BytesStatusProtocol;

import java.io.IOException;

public class BytesRecvResponder {

    public void sendBytesRecv(IOHolder ioHolder) throws IOException,SocketExceptions {
        ioHolder.getOutputStream().send((BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol()+"king kong").getBytes());
    }
    public void sendBytesNotRecv(IOHolder ioHolder) throws IOException,SocketExceptions{
        ioHolder.getOutputStream().send(BytesStatusProtocol.BYTES_SEND_FAILED.completeProtocol().getBytes());
    }
}
