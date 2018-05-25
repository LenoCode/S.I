package test_client;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

public class Notificationer extends NotificationerActions {
    protected Notificationer(DataTradeModel[] objects) {
        super(objects);
    }
}
