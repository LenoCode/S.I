package novo;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.ExceptionHandleMethod;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.lang.reflect.Method;

public class Main {
    private int a;

    public static void main(String[] args){
        Main main = new Main();

    }

    @ExceptionHandleMethod(identification = "staro")
    public void test(){

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
