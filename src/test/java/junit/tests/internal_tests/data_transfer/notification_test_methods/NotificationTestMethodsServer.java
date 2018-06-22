package junit.tests.internal_tests.data_transfer.notification_test_methods;


import junit.tests.statics.static_objects.thread_communicators.ThreadCounterCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.StreamOpen;

import java.io.IOException;

import static junit.tests.statics.static_methods.StaticMethods.*;
import static org.assertj.core.api.Assertions.*;

@ClassIdentifier(identification = "notificationtestmethod")
public class NotificationTestMethodsServer extends DataTrade {

    public static String CLASS_IDENT = "notificationtestmethod";

    private final ThreadCounterCommunicator threadCounterCommunicator = ThreadCounterCommunicator.getThreadCounterCommunicator();



    @MethodIdentifier(identification = "test01_server")
    @StreamOpen
    public void checkIfServerCanCommunicateSomePeriodOfTime(String notification,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        System.out.println("server "+notification);
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(0);
        threadCounterCommunicator.increase();
        if (threadCounterCommunicator.getCounter() < 1000){
            send(CLASS_IDENT,"test01_client","message count:"+threadCounterCommunicator.getCounter());
        }else{
            closeStream();
        }
    }
    @MethodIdentifier(identification = "test02_server")
    @StreamOpen
    public void checkIfUploadIsWorkingProperly(String notification, NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions{
        if (notification.equals("send me file")){
            send("notificationtestmethod","test02_client","I will send you file of 30 bytes");
        }else if (notification.equals("ready")){
            byte[] bytes = new byte[30];
            upload(bytes);
            System.out.println("posalo 30 bytova");
        }else if (notification.equals("finished")){
            closeStream();
        }
    }


    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
