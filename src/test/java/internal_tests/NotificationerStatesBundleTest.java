package internal_tests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import socket_installer.SI.client.socket.Client;
import socket_installer.SI.socket_creation.client.client_creator.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.protocol.enum_protocols.data_protocol.DataProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;
import socket_installer_test_environment.socket_parts.notificationers.ClientNotificationer;
import socket_installer_test_environment.socket_parts.notifications.basic_communication_notification.BasicCommunicationClientNotification;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import static socket_installer_test_environment.tools.static_methods.StaticMethods.sleep;

public class NotificationerStatesBundleTest {


    @Test
    public void checkIfKeysAreFormattedCorrectly(){
        NotificationerStatesBundle notificationerStatesBundle = new NotificationerStatesBundle();

        notificationerStatesBundle.addState("test","test1","key",200);
        notificationerStatesBundle.addState("test","test1","key1",300);
        Integer integer = notificationerStatesBundle.getState("test","test1","key");
        Integer integer1 = notificationerStatesBundle.getState("test","test1","key1");
        Assertions.assertThat(integer).isEqualTo(200);
        Assertions.assertThat(integer1).isEqualTo(300);
        notificationerStatesBundle.clearState();
        Integer nullObject = notificationerStatesBundle.getState("test","test1","key1");
        Assertions.assertThat(nullObject).isEqualTo(null);
    }


    public static void  main(String[] args) throws IOException, SocketExceptions {
        System.out.println("</SOCKET_CLOSED>Socket closed>".length());
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("192.168.43.197", 3000));
        Scanner in = new Scanner(System.in);
        String message = DataProtocol.sendMessageFormat("K", "METHOD", "FILIP CACIC");
        String message1 = DataProtocol.sendMessageFormat("K", "METHOD", "FILIP CACIC");
        message = message1 + message;
        OutputStream outputStream = socket.getOutputStream();
        System.out.println(message);

        String line;
        while (!(line = in.nextLine()).equals("EXIT")) {
            outputStream.write(message.getBytes());
            outputStream.flush();
        }
    }
}