package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder.NotificationerObjects;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.actionHolder.actions.notification_parser.NotificationParser;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public abstract class NotificationerActions <A extends DataTradeModel> extends NotificationerObjects {

    protected NotificationerActions(A[] objects) {
        super(objects);

    }

    public Iterator<String> getUnparsedIteratorNotification(String string) throws IOException, SocketExceptions {
        return notificationParser.getUnparsedIteratorNotification(string);
    }

    public void notificationProcess(String notificication) throws IOException, NoSolutionForException {
        A object = (A)annotationParser.identifyClass(objects,notificication);
        Method method = annotationParser.identifyMethod(object.getClass().getMethods(),notificication);
        notificication = extractMessage( annotationParser.removeIdents(notificication) );
        invokeMethod(object,method,notificication);
    }

    private void invokeMethod(A object,Method method, Object ... args) throws NoSolutionForException {
        try {
            method.invoke(object,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke illegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke invocationTargetException");
        }
    }
    private String extractMessage(String string){
        return notificationParser.extractNotification(string);
    }

}
