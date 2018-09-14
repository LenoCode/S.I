package socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions;

import java.util.HashMap;

public class NotificationerStatesBundle {
    private final HashMap<String,Object> stateObjects;
    private final String keyTemplate = "%s>%s<|%s";

    public NotificationerStatesBundle(){
        stateObjects = new HashMap<>();
    }

    public <A> A getState(String classIdentification,String methodIdentification,String key){
        String formatedKey = String.format(keyTemplate,classIdentification,methodIdentification,key);

        if (stateObjects.containsKey(formatedKey)){
            return (A) stateObjects.get(formatedKey);
        }else{
            return null;
        }
    }
    public <A> void addState(String classIdentification,String methodIdentification,String key,A state){
        String formatedKey = String.format(keyTemplate,classIdentification,methodIdentification,key);
        stateObjects.put(formatedKey,state);
    }
    public void clearState(){
        stateObjects.clear();
    }

}
