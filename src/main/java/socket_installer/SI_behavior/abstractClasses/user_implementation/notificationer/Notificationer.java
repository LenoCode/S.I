package socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer;


import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.NotificationerModel;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.io.IOException;

public abstract class Notificationer implements NotificationerModel {

    private ClientSocket clientSocket;

    public void sendMessage(String message) throws IOException,SocketExceptions {
        message = String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message);
        PacketRequest packetHolder = new PacketRequest(clientSocket,message);
        PacketProcessor.getPacketProcessor(clientSocket).sendPacket(packetHolder);
    }

    public void setClientSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
