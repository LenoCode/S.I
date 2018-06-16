package junit.tests.internal_tests.data_transfer.notification_test_methods;


import junit.tests.statics.static_objects.thread_communicators.ThreadCounterCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_parts.protocol.protocol_object.defined_protocol.defined_automated_responder.DefinedAutomatedResponder;

import java.io.IOException;

import static junit.tests.statics.static_methods.StaticMethods.*;
import static org.assertj.core.api.Assertions.*;

@ClassIdentifier(identification = "notificationtestmethod")
public class NotificationTestMethods extends DataTrade {

    public static String CLASS_IDENT = "notificationtestmethod";

    private final ThreadCounterCommunicator threadCounterCommunicator = ThreadCounterCommunicator.getThreadCounterCommunicator();

    @MethodIdentifier(identification = "test01")
    public void checkIfMessageRecvIsEqual(String notification,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        System.out.println("NOTIFICATOIN : "+notification);
        notificationerStatesBundle.setNewObject("Test","OBJECKT");
        send("notificationtestmethod","test02","RESPONSE");
        //assertThat(notification).isEqualTo("testingMessage");
    }
    @MethodIdentifier(identification = "test02")
    public void test(String notification,NotificationerStatesBundle notificationerStatesBundle){
        System.out.println("NOTIFICATOIN : "+notification);
        notificationerStatesBundle.setNewObject("Test","OBJECKT");
        //assertThat(notification).isEqualTo("testingMessage");
    }


    @MethodIdentifier(identification = "test02_server")
    public void checkIfClientGetResponse_serverSend(String notification) throws IOException, SocketExceptions {
        notification = "Response : "+notification;
        send(CLASS_IDENT,"test02_client",notification);
    }
    @MethodIdentifier(identification = "test02_client")
    public void checkIfClientGetResponse_clientRecieve(String notification){
        assertThat(notification).isEqualTo("Response : waiting response");
    }


    @MethodIdentifier(identification = "test03_server")
    public void checkIfServerCanCommunicateSomePeriodOfTime(String notification) throws IOException, SocketExceptions {
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(0);

        if (threadCounterCommunicator.getCounter() < 100000){
            threadCounterCommunicator.increase();
            send(CLASS_IDENT,"test03_client","message count:"+threadCounterCommunicator.getCounter());
        }
    }
    @MethodIdentifier(identification = "test03_client")
    public void checkIfClientCanCommunicateSomePeriodOfTime(String notification) throws IOException, SocketExceptions {
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(1);
        threadCounterCommunicator.increase();
        send(CLASS_IDENT,"test03_server","message count:"+threadCounterCommunicator.getCounter());
    }


    @MethodIdentifier(identification = "test04_server")
    public void checkIfServerCanCommunicateSomePeriodOfTimeWithClosingConnection(String notification, NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(0);

        if (threadCounterCommunicator.getCounter() < 10000){
            threadCounterCommunicator.increase();
            send(CLASS_IDENT,"test04_client","message count:"+threadCounterCommunicator.getCounter());
        }
    }
    @MethodIdentifier(identification = "test04_client")
    public void checkIfClientCanCommunicateSomePeriodOfTimeWithClosingConnection(String notification) throws IOException, SocketExceptions {
        assertThat(notification).matches("(message count:)\\d*");
        assertThat(threadCounterCommunicator.getCounter() % 2).isEqualTo(1);
        threadCounterCommunicator.increase();
        sleep(50);
        send(CLASS_IDENT,"test04_server","message count:"+threadCounterCommunicator.getCounter());
    }

    @MethodIdentifier(identification = "test05_server")
    public void checkIfServerCanSendMultipleResponse_server(String notification) throws IOException, SocketExceptions {
        assertThat(notification).matches("send multiple response");

        for (int i = 0; i < 10 ; i++){
            send(CLASS_IDENT,"test05_client","message count:"+i);
        }
        DefinedAutomatedResponder.getDefinedAutomatedResponder().sendStreamClosed(getClientSocket().getIOHolder());
    }
    @MethodIdentifier(identification = "test05_client")
    public void checkIfServerCanSendMultipleResponse_client(String notification) throws IOException, SocketExceptions {
        assertThat(notification).matches("(message count:)\\d*");
        System.out.println(notification);
    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        System.out.println(notificationerStatesBundle.getObject("Test"));
        return false;
    }
}
