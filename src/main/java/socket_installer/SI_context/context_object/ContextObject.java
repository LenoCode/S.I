package socket_installer.SI_context.context_object;

import socket_installer.SI_behavior.interfaces.context.ContextObjectsModel;

public class ContextObject <A> implements ContextObjectsModel{

    private A contextObject;
    private String objectId;

    @Override
    public A getObject(){
        return contextObject;
    }
    public void setContextObject(A contextObject){
        if (this.contextObject == null){
            this.contextObject = contextObject;
            objectId = contextObject.getClass().getSimpleName();
        }
    }
    @Override
    public String getObjectId() {
        return objectId;
    }
}
