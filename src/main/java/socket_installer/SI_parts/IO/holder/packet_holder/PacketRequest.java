package socket_installer.SI_parts.IO.holder.packet_holder;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;

public class PacketRequest extends PacketHolder {
    private final String request;

    public PacketRequest(ClientSocket clientSocket, String request) {
        super(clientSocket);
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
