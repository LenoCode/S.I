package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder.NotificationerObjects;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class NotificationerActions <A> extends NotificationerObjects {

    protected NotificationerActions(A[] objects) {
        super(objects);
    }

    public void notificationProcess(String notificication) throws IOException {
        A object = (A)annotationParser.identifyClass(objects,notificication);
        Method method = annotationParser.identifyMethod(object.getClass().getMethods(),notificication);
        notificication = annotationParser.removeIdents(notificication);

        invokeMethod(object,method,notificication);
    }

    private void invokeMethod(A object,Method method, Object ... args){
        try {
            method.invoke(object,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
