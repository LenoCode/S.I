package socket_installer.SI_parts.actionHolder;

import socket_installer.SI_parts.actionHolder.actions.notification_parser.NotificationParser;
import socket_installer.SI_parts.actionHolder.actions.buffer_checker.BufferChecker;

public class ActionHolder {

    private final BufferChecker bufferChecker;
    private final NotificationParser notificationParser;


    public ActionHolder (){
        bufferChecker = new BufferChecker();
        notificationParser = new NotificationParser();

    }

    public BufferChecker getBufferChecker() {
        return bufferChecker;
    }

    public NotificationParser getNotificationParser() {
        return notificationParser;
    }
}
