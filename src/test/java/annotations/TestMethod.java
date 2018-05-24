package annotations;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.response_annotation.ResponsesMethod;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;

@ResponsesMethod
@ClassIdentifier(identification = "TestMethod")
public class TestMethod{

    /*
    * MOZDA MI NETREBA OVAJ SENDRESPONSEATTRIBUTE, MOGU NAPRAVITI NEKU APSTRAKTNU KLASU KOJA CE SLATI RESPONSE, A USER CE SAM POZIVATI TE REQUEST METODE
    * TREBA PROMJENITI IME NA RECIIVE METHOD I SENDMETHOD
    * */

    @MethodIdentifier(identification = "method")
    public String methodTest(String data){
        System.out.println(data);
        return "Kingkong se mora takoder isprintati";
    }
}
