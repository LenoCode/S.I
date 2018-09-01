package socket_installer_test_environment.socket_parts.notificationers;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.connected_client_notificationer_actions.ConnectedClientNotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class ServerNotificationer extends ConnectedClientNotificationerActions {
    public ServerNotificationer(DataTradeModel[] objects) {
        super(objects);
    }
}
