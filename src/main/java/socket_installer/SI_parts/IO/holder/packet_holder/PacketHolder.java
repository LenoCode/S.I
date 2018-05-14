package socket_installer.SI_parts.IO.holder.packet_holder;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;

public abstract class PacketHolder {

    private final ClientSocket clientSocket;
    private ProcessorsEnums packetStatus;

    public PacketHolder(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        packetStatus = ProcessorsEnums.INITILIAZED;
    }


    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    public ProcessorsEnums getPacketStatus() {
        return packetStatus;
    }

    public void setPacketStatus(ProcessorsEnums packetStatus) {
        this.packetStatus = packetStatus;
    }

}
