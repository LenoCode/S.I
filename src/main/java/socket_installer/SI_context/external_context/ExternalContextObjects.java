package socket_installer.SI_context.external_context;

import socket_installer.SI_context.context_object.ContextObject;

import java.util.HashMap;

public class ExternalContextObjects extends HashMap<String,ContextObject> {

    public void addContextObject(ContextObject contextObject) {
        String key = contextObject.getObjectId();
        if (!super.containsKey(key)) {
            super.put(key, contextObject);
        }
    }
}
