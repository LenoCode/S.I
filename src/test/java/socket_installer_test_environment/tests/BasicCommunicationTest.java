package socket_installer_test_environment.tests;


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

import java.io.IOException;

import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_COMMUNICATION_CLASS_IDENT;
import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_STRING_ID;
import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_THREAD;

public class BasicCommunicationTest {

    @Rule
    public ServerResource serverResource = new ServerResource(new DataTradeModel[]{new BasicCommunicationServerNotification()});
  //@Rule
    //public ClientResource clientResource = new ClientResource( new DataTradeModel[]{new BasicCommunicationClientNotification()});

    private final AsyncCommunicator asyncCommunicator = AsyncCommunicator.getAsyncCommunicator();

    @Test
    public void checkIfConnectionsAreFineAndDataIsSendToServer() throws IOException, SocketExceptions {
       while(true){

       }
    }

    @Test
    public void checkIfDownloadUploadWorks(){

    }

}
