package socket_installer_test_environment.socket_parts.notifications.extra_data_in_buffer_notification;


import async_communicator.AsyncCommunicator;
import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;

import java.io.IOException;

import static socket_installer_test_environment.tools.static_fields.NotificationStaticFields.EXTRA_DATA_IN_BUFFER_CLASS_IDENT;

@ClassIdentifier(identification = EXTRA_DATA_IN_BUFFER_CLASS_IDENT)
public class ExtraDataInBufferNotification extends DataTrade {
    private final AsyncCommunicator asyncCommunicator =AsyncCommunicator.getAsyncCommunicator();



    public static final String EXTRA_DATA_IN_BUFFER = "ExtraDataInBuffer";
    @MethodIdentifier(identification = EXTRA_DATA_IN_BUFFER,closeStream = true)
    public void extraDataInBuffer(String notification, NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        sendNotification(EXTRA_DATA_IN_BUFFER_CLASS_IDENT,EXTRA_DATA_IN_BUFFER_CLIENT,"server");
        Integer counter = asyncCommunicator.getParameterizedObject("Counter");
        asyncCommunicator.addParameterizedObject("Counter",++counter);
    }

    public static final String EXTRA_DATA_IN_BUFFER_CLIENT = "ExtraDataInBufferClient";
    @MethodIdentifier(identification = EXTRA_DATA_IN_BUFFER_CLIENT,closeStream = false)
    public void extraDataInBufferClient(String notification, NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        sendNotification(EXTRA_DATA_IN_BUFFER_CLASS_IDENT,EXTRA_DATA_IN_BUFFER,"test");
    }




    public static final String EXTRA_DATA_IN_BUFFER_01_SERVER = "ExtraDataInBuffer01Server";
    @MethodIdentifier(identification = EXTRA_DATA_IN_BUFFER_01_SERVER,closeStream = true)
    public void extraDataInBuffer01Server(String notifcation,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        Integer oddNumber = Integer.parseInt(notifcation);
        int counter = asyncCommunicator.getParameterizedObject("Counter");

        if (counter == 999){
            asyncCommunicator.addFlag((long)10000,"TestFinished",true);
            asyncCommunicator.addParameterizedObject("Counter",counter);
        }
        if (oddNumber % 2 != 0){
            sendNotification(EXTRA_DATA_IN_BUFFER_CLASS_IDENT,EXTRA_DATA_IN_BUFFER_01_CLIENT,Integer.toString(++oddNumber));
        }
        asyncCommunicator.addParameterizedObject("Counter",++counter);



    }

    public static final String EXTRA_DATA_IN_BUFFER_01_CLIENT = "ExtraDataInBuffer01Client";
    @MethodIdentifier(identification = EXTRA_DATA_IN_BUFFER_01_CLIENT,closeStream = false)
    public void extraDataInBuffer01Client(String notifcation,NotificationerStatesBundle notificationerStatesBundle) throws IOException, SocketExceptions {
        Integer oddNumber = Integer.parseInt(notifcation);
        int counter = asyncCommunicator.getParameterizedObject("Counter");
        System.out.println("Counter" + counter );
        if (counter == 1000){
            asyncCommunicator.addFlag((long)10000,"TestFinished",true);
            asyncCommunicator.addParameterizedObject("Counter",counter);
            return;
        }

        if (oddNumber % 2 == 0){
            sendNotification(EXTRA_DATA_IN_BUFFER_CLASS_IDENT,EXTRA_DATA_IN_BUFFER_01_SERVER,Integer.toString(++oddNumber));
        }

        asyncCommunicator.addParameterizedObject("Counter",++counter);

    }



    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
