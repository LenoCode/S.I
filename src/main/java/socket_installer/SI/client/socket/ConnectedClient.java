package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.IO.wrapper.server.ConnectedClientInputStreamWrapper;
import socket_installer.SI_parts.IO.wrapper.server.ConnectedClientOutputStreamWrapper;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ConnectedClient extends ClientSocket {


    public ConnectedClient(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        ClientConfiguration clientConfiguration = (ClientConfiguration) getSocketConfiguration();

        while(clientConfiguration.isSocketOnline()){
            PacketHolder packetHolder = new PacketHolder(this);
            if (PacketProcessor.getPacketProcessor(this).checkInputStreamData(packetHolder)){
                PacketProcessor.getPacketProcessor(this).notify(this);
            }
        }
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
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
        ioHolder.setInputStream(new ConnectedClientInputStreamWrapper( new BufferedInputStream(socket.getInputStream()) ));
        ioHolder.setOutputStream(new ConnectedClientOutputStreamWrapper( new BufferedOutputStream(socket.getOutputStream()) ));
    }
}

