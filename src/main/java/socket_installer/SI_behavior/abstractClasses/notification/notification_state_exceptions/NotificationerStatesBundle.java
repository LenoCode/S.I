package socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions;

import java.util.HashMap;

public class NotificationerStatesBundle {
    private final HashMap<String,Object> hashMap;

    public NotificationerStatesBundle(){
        hashMap = new HashMap<>();
    }
    public Object getObject(String key){
        return hashMap.get(key);
    }
    public void setNewObject(String key,Object object){
        hashMap.put(key,object);
    }
}
