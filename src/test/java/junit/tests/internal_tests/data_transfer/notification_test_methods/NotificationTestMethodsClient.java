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
public class NotificationTestMethodsClient extends DataTrade {

    public static String CLASS_IDENT = "notificationtestmethod";

    private final ThreadCounterCommunicator threadCounterCommunicator = ThreadCounterCommunicator.getThreadCounterCommunicator();

    @MethodIdentifier(identification = "test01")
    public void checkIfMessageRecvIsEqual(String notification,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        assertThat(notification).isEqualTo("testingMessage");
    }

    @MethodIdentifier(identification = "test03_client")
    @StreamOpen
    public void checkIfClientCanCommunicateSomePeriodOfTime(String notification,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        System.out.println("client "+notification+"   "+threadCounterCommunicator.getCounter());
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(1);
        threadCounterCommunicator.increase();


    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
