package socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions;

public class NotificationerStateObject<A> {
    public final String classIdentification;
    public final String methodIdentification;
    public final A state;


    public NotificationerStateObject(String classIdentification, String methodIdentification, A state){
        this.classIdentification = classIdentification;
        this.methodIdentification = methodIdentification;
        this.state = state;
    }


}
