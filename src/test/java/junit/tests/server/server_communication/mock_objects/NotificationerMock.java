package junit.tests.server.server_communication.mock_objects;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.connected_client_notificationer_actions.ConnectedClientNotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class NotificationerMock extends ConnectedClientNotificationerActions {
    public NotificationerMock(DataTradeModel[] objects) {
        super(objects);
    }
}
