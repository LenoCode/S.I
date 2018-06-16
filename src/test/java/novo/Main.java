package novo;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.ExceptionHandleMethod;

import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args){
        for (Method method : Main.class.getMethods()){
            if (method.getName().equals("test")){
                System.out.println(((ExceptionHandleMethod)method.getAnnotations()[0]).identification());
            }
        }
    }

    @ExceptionHandleMethod(identification = "staro")
    public void test(){

    }
}
