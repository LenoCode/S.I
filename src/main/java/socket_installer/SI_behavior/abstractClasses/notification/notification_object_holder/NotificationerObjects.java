package socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder;



import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.AnnotationParser;

public abstract class NotificationerObjects <A extends DataTradeModel> {
    protected final AnnotationParser annotationParser;
    protected ClientSocket clientSocket;
    protected ExternalContext externalContext;
    protected String lastMethodCalled;
    protected NotificationerStatesBundle notificationerStatesBundle;
    protected final A[] objects;

    protected NotificationerObjects(A[] objects){
        lastMethodCalled = "No method called";
        annotationParser = new AnnotationParser();
        notificationerStatesBundle = new NotificationerStatesBundle();
        this.objects = objects;
    }
    public void setClientSocket(ClientSocket clientSocket){
        this.clientSocket = clientSocket;
    }

    public A[] getObjects(){
        return objects;
    }

}
