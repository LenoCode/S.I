package socket_installer.SI_parts.notification.notification_processor.annotation_parser;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.request_annotation.RequestsMethod;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.response_annotation.ResponsesMethod;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_behavior.interfaces.notification.annotation_parser.method_annotater.method_steps.MethodSteps;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.method_annotater.MethodAnnotater;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.method_annotater.request_method_steps.RequestMethodSteps;
import socket_installer.SI_parts.notification.notification_processor.annotation_parser.method_annotater.response_method_steps.ResponseMethodSteps;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParser {

    public <A> A identifyClass(A[] objects, String classIdent) throws NullPointerException{
        for (A object : objects){
            ClassIdentifier classIdentifier = object.getClass().getAnnotation(ClassIdentifier.class);

            if (classIdentifier.identification().equals(classIdent)){
                return object;
            }
        }
        throw new NullPointerException("No class method found");
    }

    public<A> MethodAnnotater identifyMethod(A object,Method[] methods, String methodIdent) throws IOException {
        final String regexExcluedMethods = "(wait|equals|toString|hashCode|getClass|notify|notifyAll)";

        for (Method method: methods){
            if (!checkIfMethodShouldBeExcluded(regexExcluedMethods,method.getName())) {
                String ident = method.getAnnotation(MethodIdentifier.class).identification();

                if (ident == null) {
                    throw new IOException("Method is missing identifier annotation");
                } else {
                    if (ident.equals(methodIdent)) {
                        return createMethodAnnotater(object, method);
                    }
                }
            }
        }
        throw new IOException("No method was found");
    }
    private boolean checkIfMethodShouldBeExcluded(String regex,String methodName){
        return methodName.matches(regex);
    }

    private <A> MethodAnnotater createMethodAnnotater(A object, Method method){
        try{
            MethodSteps methodSteps = identifyMethodSteps(object,method.getAnnotations());
            return new MethodAnnotater(object,method,methodSteps);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //I OVO MOZDA UOPCE NE TREBAM, NISTA OD OVIH IDENTIFIKACIJA NECU TREBATI MOZDA
    private<A> MethodSteps identifyMethodSteps(A object,Annotation[] annotations) throws Exception{
        ResponsesMethod responseMethod = object.getClass().getAnnotation(ResponsesMethod.class);
        RequestsMethod requestMethod = object.getClass().getAnnotation(RequestsMethod.class);

        if (requestMethod == null && responseMethod != null){
            return new ResponseMethodSteps(annotations);
        }
        else if (requestMethod != null && responseMethod == null){
            return new RequestMethodSteps();
        }
        throw new Exception("Class is not defined appropriately");
    }
}
