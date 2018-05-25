package annotations;

import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;



@ClassIdentifier(identification = "TestMethod")
public class TestMethod extends DataTrade {

    public TestMethod() {

    }

    /*
    * MOZDA MI NETREBA OVAJ SENDRESPONSEATTRIBUTE, MOGU NAPRAVITI NEKU APSTRAKTNU KLASU KOJA CE SLATI RESPONSE, A USER CE SAM POZIVATI TE REQUEST METODE
    * TREBA PROMJENITI IME NA RECIIVE METHOD I SENDMETHOD
    * */

    @MethodIdentifier(identification = "metoda")
    public void methodTest(String data){
        System.out.println(data);
    }

}
