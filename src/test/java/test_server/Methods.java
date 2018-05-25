package test_server;


import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;

@ClassIdentifier(identification = "Methods")
public class Methods extends DataTrade {

    @MethodIdentifier(identification = "testing")
    public void testing(String n){
        System.out.println(n);
    }
}
