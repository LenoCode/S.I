package junit.tests.server.server_communication.mock_objects;

import socket_installer.SI_behavior.abstractClasses.notification.data_trade.DataTrade;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.class_annotation.class_identifier.ClassIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;

@ClassIdentifier(identification = "NotificationMock")
public class NotificationerMethodsMock extends DataTrade {


    @MethodIdentifier(identification = "mockMethod")
    public void mockMethod(String notification){

    }

    public void exceptionHandler(NotificationerStatesBundle notificationerStatesBundle) {

    }

    @Override
    public boolean exceptionHandler(ClientSocket clientSocket, NotificationerStatesBundle notificationerStatesBundle) {
        return false;
    }
}
