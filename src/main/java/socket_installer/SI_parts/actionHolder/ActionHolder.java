package socket_installer.SI_parts.actionHolder;

import socket_installer.SI_parts.actionHolder.actions.notification_parser.NotificationParser;
import socket_installer.SI_parts.protocol.CommunicationProtocol;
import socket_installer.SI_parts.actionHolder.actions.buffer_checker.BufferChecker;
import socket_installer.SI_parts.actionHolder.actions.BytesRecvResponder;

public class ActionHolder {

    private final BufferChecker bufferChecker;
    private final BytesRecvResponder bytesResponder;
    private final CommunicationProtocol communicationProtocol;
    private final NotificationParser notificationParser;


    public ActionHolder (){
        bufferChecker = new BufferChecker();
        bytesResponder = new BytesRecvResponder();
        communicationProtocol = new CommunicationProtocol();
        notificationParser = new NotificationParser();

    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }
    public BytesRecvResponder getBytesResponder(){
        return bytesResponder;
    }
    public CommunicationProtocol getCommunicationProtocol() {
        return communicationProtocol;
    }

    public NotificationParser getNotificationParser() {
        return notificationParser;
    }
}
