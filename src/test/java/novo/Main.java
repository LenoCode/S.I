package novo;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.ExceptionHandleMethod;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.lang.reflect.Method;

public class Main {
    private int a;

    public static void main(String[] args){
        Main main = new Main();
        String a = TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol();
        System.out.println(a);
        String regex = "("+TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol()+"|"+TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol()+")";
        String b = a.replaceAll(regex,"novo");
        System.out.println(b);
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
