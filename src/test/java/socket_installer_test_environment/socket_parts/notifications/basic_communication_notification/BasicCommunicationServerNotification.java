package socket_installer_test_environment.socket_parts.notifications.basic_communication_notification;


import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.StreamOpen;

import java.io.IOException;

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

    public final static String CHECK_IF_SERVER_CAN_SEND_BYTES = "ChckIfSrvRecvData";
    @MethodIdentifier(identification = CHECK_IF_SERVER_CAN_SEND_BYTES,closeStream = true)
    @StreamOpen
    public void setCheckIfServerCanSendBytes(String notification,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        switch (notification){
            case "Start":
                send(BASIC_COMMUNICATION_CLASS_IDENT,CHECK_IF_SERVER_CAN_SEND_BYTES,"Size:"+"KING".getBytes().length);
            case "Ready":
                upload("KING".getBytes());
        }


    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
