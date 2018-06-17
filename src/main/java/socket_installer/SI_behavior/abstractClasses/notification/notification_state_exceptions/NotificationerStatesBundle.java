package socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions;

import java.util.HashMap;

public class NotificationerStatesBundle {
    private final HashMap<String,Object> userObjects;
    private boolean sendCloseStream;

    public NotificationerStatesBundle(){
        userObjects = new HashMap<>();
        sendCloseStream = true;
    }
    public Object getObject(String key){
        return userObjects.get(key);
    }
    public void saveObject(String key, Object object){
        userObjects.put(key,object);
    }

    public boolean isSendCloseStream() {
        return sendCloseStream;
    }
    public void keepStreamAlive(){
        sendCloseStream = false;
    }
    public void closeStream(){
        sendCloseStream = true;
    }
}
