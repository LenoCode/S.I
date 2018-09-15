package socket_installer_test_environment.tests.extra_data_in_buffer;

import async_communicator.AsyncCommunicator;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer_test_environment.rules.socket_rules.ClientResource;
import socket_installer_test_environment.rules.socket_rules.ServerResource;
import socket_installer_test_environment.socket_parts.notifications.basic_communication_notification.BasicCommunicationClientNotification;
import socket_installer_test_environment.socket_parts.notifications.basic_communication_notification.BasicCommunicationServerNotification;
import socket_installer_test_environment.socket_parts.notifications.extra_data_in_buffer_notification.ExtraDataInBufferNotification;
import java.io.IOException;
import static socket_installer_test_environment.socket_parts.notifications.extra_data_in_buffer_notification.ExtraDataInBufferNotification.EXTRA_DATA_IN_BUFFER;
import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.EXTRA_DATA_IN_BUFFER_CLASS_IDENT;
import static socket_installer_test_environment.tools.static_methods.StaticMethods.sleep;

public class ExtraDataInBufferTestServer {

    @Rule
    public ServerResource serverResource = new ServerResource(new DataTrade[]{ new ExtraDataInBufferNotification()});


    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();


    @Test
    public void checkIfSocketsCorrectlyHandlesExtraData() throws IOException, SocketExceptions {
        asyncCommunicator.initNewThread((long) 10000);
        asyncCommunicator.addParameterizedObject("Counter",0);
        asyncCommunicator.waitThreadForResponse((long) 10000);
    }


    @Test
    public void checkIfDataIsSendAndRecvCorrectly(){
        asyncCommunicator.initNewThread((long) 10000);
        asyncCommunicator.addParameterizedObject("Counter",0);
        asyncCommunicator.waitForFlag((long)10000,"TestFinished");

        Integer counter = asyncCommunicator.getParameterizedObject("Counter");
        sleep(5000);
        Assertions.assertThat(counter).isEqualTo(999);
    }
}
