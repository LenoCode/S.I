package socket_installer_test_environment.rules.socket_rules;


import org.junit.rules.ExternalResource;

import org.mockito.Mock;
import socket_installer.SI.socket_creation.server.ServerSocketCreator;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_behavior.interfaces.notification.ServerNotificationerImplModel;
import socket_installer_test_environment.socket_parts.notificationers.ServerNotificationer;
import static socket_installer_test_environment.tools.static_fields.StaticFields.HOST;
import static socket_installer_test_environment.tools.static_fields.StaticFields.PORT;
import static socket_installer_test_environment.tools.static_fields.StaticFields.TIMEOUT;
import static socket_installer_test_environment.tools.static_methods.StaticMethods.sleep;
import static socket_installer_test_environment.tools.static_methods.StaticMethods.threadRun;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.*;


public class ServerResource extends ExternalResource {

    private ServerCreatedSocket serverCreatedSocket;
    @Mock
    private ServerNotificationer notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public ServerResource(DataTradeModel[] dataTradeModels){
        this.dataTradeModels = dataTradeModels;
    }

    @Override
    protected void before() throws Throwable {
        notificationerMock = new ServerNotificationer(dataTradeModels);
        ServerNotificationerImplModel serverNotificationerImplModel = new ServerNotificationerImplModel() {
            @Override
            public NotificationerActions getNotificationerAction() {
                return notificationerMock;
            }
        };
        serverCreatedSocket = ServerSocketCreator.createServer(HOST,serverNotificationerImplModel,PORT,1,TIMEOUT);
        threadRun(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Server starting");
                    serverCreatedSocket.initSocket();
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

    @Override
    protected void after() {
        serverCreatedSocket.closeProgram();
        sleep(1000);
        assertThat(serverCreatedSocket.isServerRunning()).isEqualTo(false);
        System.out.println("Server closed");
    }

    public ServerNotificationer getNotificationerMock() {
        return notificationerMock;
    }

    public ServerCreatedSocket getServerCreatedSocket() {
        return serverCreatedSocket;
    }

    public void setDataTradeModels(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }
}
