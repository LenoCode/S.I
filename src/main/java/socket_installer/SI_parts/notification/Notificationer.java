package socket_installer.SI_parts.notification;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.AnnotationParser;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.method_annotater.MethodAnnotater;

import java.io.IOException;


public class Notificationer<A>  {
    private final A[] objects;
    private final AnnotationParser annotationParser;

    public Notificationer(A[] objects) {
        this.objects = objects;
        annotationParser = new AnnotationParser();

    }

    public void notificationProcess(ClientSocket socket,String notification) throws IOException,SocketExceptions {
        A object = annotationParser.identifyClass(objects,notification);
        MethodAnnotater methodAnnotater = annotationParser.identifyMethod(object,object.getClass().getMethods(),notification);
        methodAnnotater.runSteps(socket,notification);
    }


}