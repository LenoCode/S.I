package socket_installer_test_environment.socket_parts.notifications.basic_communication_notification;


import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;

import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_COMMUNICATION_CLASS_IDENT;
import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_STRING_ID;
import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_THREAD;


@ClassIdentifier(identification = BASIC_COMMUNICATION_CLASS_IDENT)
public class BasicCommunicationServerNotification extends DataTrade {
    private final AsyncCommunicator asyncCommunicator =AsyncCommunicator.getAsyncCommunicator();

    public final static String CHECK_IF_SERVER_RECV_DATA = "CheckIFServerRecvData";
    @MethodIdentifier(identification = CHECK_IF_SERVER_RECV_DATA ,closeStream = true)
    public void checkIfServerRecvData(String notification,NotificationerStatesBundle notificationerStatesBundle){
        asyncCommunicator.initNewThread(BASIC_THREAD);
        asyncCommunicator.addFlag(BASIC_THREAD,BASIC_STRING_ID,true);
    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
