package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;

import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketResponse;
import socket_installer.SI_parts.IO.wrapper.client.ClientInputStreamWrapper;
import socket_installer.SI_parts.IO.wrapper.client.ClientOutputStreamWrapper;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends ClientSocket {


    public Client(Socket clientSocket) {
        super(clientSocket);
    }

    public boolean sendMessage(PacketRequest packetHolder)throws  IOException, SocketExceptions{
        return PacketProcessor.getPacketProcessor(this).sendPacket(packetHolder);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        PacketHolder packetHolder = new PacketResponse(this);
        if (PacketProcessor.getPacketProcessor(this).checkInputStreamData(packetHolder)){
            PacketProcessor.getPacketProcessor(this).notify(this);
        }
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
    }

    public void reconnectSocket() throws IOException, SocketExceptions{
        deactivateSocket();
        Socket socket = new Socket(socketConfiguration.getIpAddress(),socketConfiguration.getPort());
        System.out.println("SIMULIRAM CRASH MREZE -");
        System.out.println("SIMULIRAM CRACH MREZE -----");
        System.out.println("KONEKCIJA PONOVO USPOSTAVLJENA, SUSTAV OZIVLJEN........");
        replaceSocket(socket);
    }

    @Override
    public void replaceSocket(Socket socket) throws IOException, SocketExceptions {
        this.socket = socket;
        ioHolder.getStringBuffer().emptyBuffer();
        setupStream(socket);
        ((ClientConfiguration)socketConfiguration).setStreamPaused(false);
        socketConfiguration.setSocketOnlineStatus(true);
    }
    private void setupStream(Socket socket) throws IOException, SocketExceptions{
        ioHolder.setInputStream(new ClientInputStreamWrapper( new BufferedInputStream(socket.getInputStream()) ));
        ioHolder.setOutputStream(new ClientOutputStreamWrapper( new BufferedOutputStream(socket.getOutputStream()) ));
    }
}
