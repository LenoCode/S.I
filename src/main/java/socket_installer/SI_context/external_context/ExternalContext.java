package socket_installer.SI_context.external_context;

import socket_installer.SI_context.context_object.ContextObject;

public class ExternalContext {

    private final ExternalContextObjects externalContextObjects;

    public ExternalContext(){
        externalContextObjects = new ExternalContextObjects();
    }

    public void saveContextObject(ContextObject contextObject){
        externalContextObjects.addContextObject(contextObject);
    }
    public ContextObject getContextObject(String key){
        return  externalContextObjects.get(key);
    }
}
