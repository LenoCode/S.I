package socket_installer_test_environment.socket_parts.notifications.basic_communication_notification;


import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.StreamOpen;

import java.sql.SQLOutput;

import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.BASIC_COMMUNICATION_CLASS_IDENT;

@ClassIdentifier(identification = BASIC_COMMUNICATION_CLASS_IDENT)
public class BasicCommunicationClientNotification extends DataTrade {
    private final AsyncCommunicator asyncCommunicator =AsyncCommunicator.getAsyncCommunicator();

    public final static String CHECK_IF_SERVER_CAN_SEND_BYTES = "ChckIfSrvRecvData";
    @MethodIdentifier(identification = CHECK_IF_SERVER_CAN_SEND_BYTES)
    public void setCheckIfServerCanSendBytes(String notification,NotificationerStatesBundle notificationerStatesBundle){
        System.out.println("CLIENT CLOSING    "+notification);
    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
