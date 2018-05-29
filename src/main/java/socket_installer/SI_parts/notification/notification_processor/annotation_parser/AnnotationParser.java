package socket_installer.SI_parts.notification.notification_processor.annotation_parser;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import java.io.IOException;
import java.lang.reflect.Method;

public class AnnotationParser {
    private final String CLASS_IDENT = "classIdent:";
    private final int CLASS_IDENT_LENGTH = CLASS_IDENT.length();
    private final String METHOD_IDENT = "methodIdent:";
    private final int METHOD_IDENT_LENGTH = METHOD_IDENT.length();
    private final String MESSAGE_IDENT = "message:";
    private final int MESSAGE_IDENT_LENGTH = MESSAGE_IDENT.length();
    private final String EXCLUDED_METHODS = "(wait|equals|toString|hashCode|getClass|notify|notifyAll)";


    public <A> A identifyClass(A[] objects, String notification) throws NullPointerException{
        final String classIdent = getClassIdent(notification);

        for (A object : objects){
            ClassIdentifier classIdentifier = object.getClass().getAnnotation(ClassIdentifier.class);

            if (classIdentifier.identification().equals(classIdent)){
                return object;
            }
        }
        throw new NullPointerException("No class method found");
    }

    public<A> Method identifyMethod(Method[] methods, String notification) throws IOException {
        final String methodIdent = getMethodIdent(notification);

        for (Method method: methods){
            if (!checkIfMethodShouldBeExcluded(method.getName())) {
                String ident = method.getAnnotation(MethodIdentifier.class).identification();

                if (ident == null) {
                    throw new IOException("Method is missing identifier annotation");
                } else {
                    if (ident.equals(methodIdent)) {
                        return method;
                    }
                }
            }
        }
        throw new IOException("No method was found");
    }
    private boolean checkIfMethodShouldBeExcluded(String methodName){
        return methodName.matches(EXCLUDED_METHODS);

    }
    private String getClassIdent(String notification){
        int startIndex = notification.indexOf(CLASS_IDENT) + CLASS_IDENT_LENGTH;
        int endIndex = notification.indexOf(METHOD_IDENT)-1;
        return notification.substring(startIndex,endIndex);
    }
    private String getMethodIdent(String notification){
        int startIndex = notification.indexOf(METHOD_IDENT)+METHOD_IDENT_LENGTH;
        int endIndex = notification.indexOf(MESSAGE_IDENT)-1;
        return notification.substring(startIndex,endIndex);
    }
    public String removeIdents(String notification){
        int indexOfClassIdent = notification.indexOf(CLASS_IDENT);
        int indexOfMessage = notification.indexOf(MESSAGE_IDENT)+MESSAGE_IDENT_LENGTH;
        String partToRemove = notification.substring(indexOfClassIdent,indexOfMessage);
        return notification.replace(partToRemove,"");
    }
}
