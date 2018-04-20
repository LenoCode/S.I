package context;

import socket_installer.SI_parts.context.ContextObject;
import socket_installer.SI_context.internal_context.InternalContext;

public class Test02 {


    public static void main(String[] args){
        try {
            InternalContext.createContext();
            InternalContext context = InternalContext.getInternalContext();

            ContextObject<String> k = new ContextObject();

            k.setContextObject("King kong");

            context.saveContextObject(k);

            System.out.println(context.getContextObject("String").getObject().getClass().getSimpleName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
