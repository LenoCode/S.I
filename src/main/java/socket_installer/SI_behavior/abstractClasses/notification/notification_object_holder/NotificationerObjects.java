package socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder;



import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.AnnotationParser;

public abstract class NotificationerObjects <A extends DataTradeModel> {
    protected   AnnotationParser annotationParser;
    protected final A[] objects;

    protected NotificationerObjects(A[] objects){
        annotationParser = new AnnotationParser();
        this.objects = objects;
    }
    public A[] getObjects(){
        return objects;
    }
}
