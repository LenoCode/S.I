package socket_installer.SI_behavior.interfaces.communication_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;


import java.io.IOException;

public interface PacketProcessorModel {

    boolean sendPacket(PacketHolder packetRequest) throws IOException, SocketExceptions;
    boolean checkInputStreamData(PacketHolder packetHolder) throws IOException, SocketExceptions;
    boolean isPacketSending(PacketHolder packetHolder)throws IOException, SocketExceptions;
    boolean isDataUncomplete(PacketHolder packetRequest) throws IOException, SocketExceptions;
    void notify(ClientSocket clientSocket) throws IOException,SocketExceptions;

}
