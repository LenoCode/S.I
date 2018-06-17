package junit.tests.internal_tests.data_transfer.notificationer_mocks;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.connected_client_notificationer_actions.ConnectedClientNotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class ServerNotificationer extends ConnectedClientNotificationerActions {
    public ServerNotificationer(DataTradeModel[] objects) {
        super(objects);
    }
}
