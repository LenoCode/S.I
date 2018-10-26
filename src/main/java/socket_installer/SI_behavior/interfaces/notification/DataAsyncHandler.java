package socket_installer.SI_behavior.interfaces.notification;

import socket_installer.SI_parts.notification.data_trade.data_observers.DataObservers;

public interface DataAsyncHandler {
    void handleData(DataObservers dataObservers, String[] signal);
}
