package socket_installer.SI.server.socket_actions.notificationer_actions_initializator;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ServerNotificationerInitializator<A extends NotificationerActions> {
    private final Class<A> classNotification;
    private final DataTradeModel[] dataTrades;

    public ServerNotificationerInitializator(Class<A> classNotification, DataTradeModel[] dataTrades){
        this.classNotification = classNotification;
        this.dataTrades = dataTrades;
    }


    public A initializedNotificationerActions(){
        try {
            Constructor<A> constructor = (Constructor<A>) classNotification.getConstructor(DataTradeModel[].class);
            return constructor.newInstance((Object)dataTrades);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new NullPointerException("NotificationerAction class constructor broke down,couldn't create instance");
        }
    }

}
