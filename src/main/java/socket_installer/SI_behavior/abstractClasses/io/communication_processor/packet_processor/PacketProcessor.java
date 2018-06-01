package socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor;


import socket_installer.SI.client.socket.Client;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.PacketProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_processor.ClientPacketProcessor;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_processor.ConnectedClientProcessor;
import socket_installer.SI_parts.IO.communication_processor.processors.packet_status_processor.PacketStatusProcessor;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.actionHolder.actions.notification_parser.NotificationParser;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;
import socket_installer.SI_parts.protocol.protocol_object.defined_protocol.defined_automated_responder.DefinedAutomatedResponder;


import java.io.IOException;
import java.util.Iterator;

import static socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums.INITILIAZED;
import static socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums.REINITILIAZED;


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
    public boolean sendPacket(PacketHolder packetHolder) throws IOException, SocketExceptions {
        String message = packetHolder.getData();

        while (isPacketSending(packetHolder)) {
            ProcessorsEnums status = packetHolder.getPacketStatus();
            if (status == INITILIAZED || status == REINITILIAZED) {
                packetStatusProcessor.checkSendPacketStatus(packetHolder, message);
            }
            packetStatusProcessor.checkReadPacketStatus(packetHolder);
        }
        return true;
    }
    @Override
    public boolean checkInputStreamData(PacketHolder packetHolder) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetHolder.getClientSocket();

        while(isDataUncomplete(packetHolder)){
            packetStatusProcessor.checkReadPacketStatus(packetHolder);
        }
        DefinedAutomatedResponder.getDefinedAutomatedResponder().sendBytesSuccessProtocol(clientSocket.getIOHolder());
        return true;
    }

    @Override
    public void notify(ClientSocket clientSocket) throws IOException, SocketExceptions {
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        NotificationerActions notificationer = clientSocket.getNotificationer();
        Iterator<String> iterator = notificationer.getUnparsedIteratorNotification(stringBuffer.getString());

        stringBuffer.emptyBuffer();

        while(iterator.hasNext()) {
            String next = iterator.next();
            notificationer.notificationProcess(next);
        }
    }

}
