package junit.tests.internal_tests.data_transfer.notificationer_mocks;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.client_notificationer_action.ClientNotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class ClientNotificationer extends ClientNotificationerActions {
    public ClientNotificationer(DataTradeModel[] objects) {
        super(objects);
    }
}
