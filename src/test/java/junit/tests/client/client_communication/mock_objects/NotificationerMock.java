package junit.tests.client.client_communication.mock_objects;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.client_notificationer_action.ClientNotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class NotificationerMock extends ClientNotificationerActions {
    public NotificationerMock(DataTradeModel[] objects) {
        super(objects);
    }
}
