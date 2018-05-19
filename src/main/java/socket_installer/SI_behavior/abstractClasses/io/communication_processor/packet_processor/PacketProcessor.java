package socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor;


import socket_installer.SI.client.socket.Client;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer.Notificationer;
import socket_installer.SI_behavior.interfaces.communication_processor.PacketProcessorModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_processor.ClientPacketProcessor;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_processor.ConnectedClientProcessor;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_status_processor.PacketStatusProcessor;
import socket_installer.SI_parts.actionHolder.actions.notification_parser.NotificationParser;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;


import java.io.IOException;
import java.util.Iterator;


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

    @Override
    public void notify(ClientSocket clientSocket) throws IOException, SocketExceptions {
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        NotificationParser notificationParser = clientSocket.getActions().getNotificationParser();
        Notificationer notificationer = clientSocket.getNotificationer();

        Iterator<String> iterator = notificationParser.getUnparsedIteratorNotification(stringBuffer.getString());
        stringBuffer.emptyBuffer();

        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
            notificationer.callAppropriateMethod(notificationParser.extractNotification(next));
        }
    }

}
