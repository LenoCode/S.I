package socket_installer.SI_parts.IO.communication_processor.processors.packet_processor;


import socket_installer.SI.client.socket.Client;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.PacketProcessorModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_status_processor.PacketStatusProcessor;


import java.io.IOException;


public abstract class PacketProcessor implements PacketProcessorModel {
    private static ClientPacketProcessor clientPacketProcessor;
    private static ConnectedClientProcessor connectedClientProcessor;

    protected final PacketStatusProcessor packetStatusProcessor;

    protected PacketProcessor(){
        packetStatusProcessor = new PacketStatusProcessor();
    }
    public static PacketProcessor getPacketProcessor(ClientSocket clientSocket){
        if (clientPacketProcessor == null){
            clientPacketProcessor = new ClientPacketProcessor();
        }
        if (connectedClientProcessor == null){
            connectedClientProcessor = new ConnectedClientProcessor();
        }
        return (clientSocket instanceof Client) ? clientPacketProcessor : connectedClientProcessor;
    }


    protected void sendData(String message, OutputStreamWrapperModel outputStreamWrapper) throws IOException,SocketExceptions {
        outputStreamWrapper.send(message.getBytes());
    }

}
