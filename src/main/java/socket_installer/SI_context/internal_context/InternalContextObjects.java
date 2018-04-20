package socket_installer.SI_context.internal_context;

import socket_installer.SI_parts.context.ContextObject;

import java.util.HashMap;

public class InternalContextObjects extends HashMap<String,ContextObject> {

    public void addContextObject(ContextObject contextObject){
        String key = contextObject.getObjectId();
        super.put(key,contextObject);
    }
}