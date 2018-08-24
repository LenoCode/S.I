package novo;

import junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsServer;
import junit.tests.internal_tests.data_transfer.notificationer_mocks.ServerNotificationer;
import socket_installer.SI.socket_creation.server.ServerSocketCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_behavior.interfaces.notification.ServerNotificationerImplModel;

import java.io.IOException;

import static junit.tests.statics.static_fields.StaticFields.HOST;
import static junit.tests.statics.static_fields.StaticFields.PORT;
import static junit.tests.statics.static_methods.StaticMethods.sleep;
import static junit.tests.statics.static_methods.StaticMethods.threadRun;

public class Server {

    private ServerCreatedSocket serverCreatedSocket;

    private ServerNotificationer notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public Server(DataTradeModel[] dataTradeModels){
        this.dataTradeModels = dataTradeModels;
    }


    protected void start() throws Throwable {
        notificationerMock = new ServerNotificationer(dataTradeModels);
        serverCreatedSocket = ServerSocketCreator.createServer(HOST, (ServerNotificationerImplModel) notificationerMock,PORT,1,10);
        threadRun(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Server starting");
                    serverCreatedSocket.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        });
        sleep(1000);
        System.out.println("Server is up");
    }

    public static void main(String[] args){
        Server server = new Server(new DataTradeModel[]{new NotificationTestMethodsServer()});
        try {
            server.start();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
