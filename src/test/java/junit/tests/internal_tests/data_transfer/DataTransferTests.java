package junit.tests.internal_tests.data_transfer;

import junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsClient;
import junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsServer;
import junit.tests.rules.ClientResource;
import junit.tests.rules.ServerResource;
import junit.tests.statics.static_objects.thread_communicators.ThreadCounterCommunicator;
import org.junit.Rule;
import org.junit.Test;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import static org.assertj.core.api.Assertions.*;



import java.io.IOException;

import static junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsServer.CLASS_IDENT;


public class DataTransferTests {

    @Rule
    public ServerResource serverResource = new ServerResource(new DataTradeModel[]{(DataTradeModel) new NotificationTestMethodsServer()});
    @Rule
    public ClientResource clientResource = new ClientResource( new DataTradeModel[]{(DataTradeModel) new NotificationTestMethodsClient()} );

    //THREAD COMMUNICATORS
    private final ThreadCounterCommunicator threadCounterCommunicator = ThreadCounterCommunicator.getThreadCounterCommunicator();




    @Test()
    public void checkIfClientAndServerCanCommunicateSomePeriodOfTime() throws IOException, SocketExceptions {
        clientResource.getNotificationerMock().sendNotification(CLASS_IDENT,"test03_server","message count:0");

        assertThat(threadCounterCommunicator.getCounter()).isEqualTo(1000);
        threadCounterCommunicator.finish();
        System.out.println("Server and client exchanged 100000 messages");
    }






}
