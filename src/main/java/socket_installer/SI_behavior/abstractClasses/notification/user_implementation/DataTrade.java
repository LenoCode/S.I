package socket_installer.SI_behavior.abstractClasses.notification.user_implementation;

import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.io.IOException;

public abstract class DataTrade implements DataTradeModel {
    private ClientSocket clientSocket;
    @Override
    public void setClientSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void send(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions {
        String completeString = ClientProtocol.sendMessageFormat(classIdent,methodIdent,data);
        PacketHolder packetHolder = new PacketHolder(clientSocket);
        packetHolder.setData(completeString);
        PacketProcessor.getPacketProcessor(clientSocket).sendPacket(packetHolder);
    }
}
