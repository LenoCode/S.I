package socket_installer.SI_context.internal_context;

import socket_installer.SI_parts.context.ContextObject;

public class InternalContext {
    private static InternalContext internalContext;
    private final InternalContextObjects internalContextObjects;

    private InternalContext(){
        internalContextObjects = new InternalContextObjects();
    }

    public static void createContext() {
        try {
            if (internalContext == null) {
                internalContext = new InternalContext();
            } else {
                throw new Exception("Context already initialized");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InternalContext getInternalContext(){
        try{
            if (internalContext != null){
                return internalContext;
            }else{
                throw new Exception("No internal context");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void saveContextObject(ContextObject contextObject){
        if (internalContext != null){
            internalContextObjects.addContextObject(contextObject);
        }
    }
    public ContextObject getContextObject(String key){
        return  internalContextObjects.get(key);
    }

}
