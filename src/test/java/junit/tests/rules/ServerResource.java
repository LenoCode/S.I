package junit.tests.rules;

import junit.tests.server.server_communication.mock_objects.NotificationerMethodsMock;
import junit.tests.server.server_communication.mock_objects.NotificationerMock;
import org.junit.rules.ExternalResource;
import org.mockito.Mock;
import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

import java.io.IOException;

import static junit.tests.statics.static_fields.StaticFields.HOST;
import static junit.tests.statics.static_fields.StaticFields.PORT;
import static junit.tests.statics.static_fields.StaticFields.TIMEOUT;
import static org.assertj.core.api.Assertions.*;
import static junit.tests.statics.static_methods.StaticMethods.*;

public class ServerResource extends ExternalResource {

    private ServerCreatedSocket serverCreatedSocket;
    @Mock
    private NotificationerMock notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public ServerResource(DataTradeModel[] dataTradeModels){
        this.dataTradeModels = dataTradeModels;
    }

    @Override
    protected void before() throws Throwable {
        notificationerMock = new NotificationerMock(dataTradeModels);
        serverCreatedSocket = ServerCreator.createServer(HOST,notificationerMock,PORT,1,TIMEOUT);
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

    @Override
    protected void after() {
        serverCreatedSocket.closeProgram();
        sleep(1000);
        assertThat(serverCreatedSocket.isServerRunning()).isEqualTo(false);
        System.out.println("Server closed");
    }

    public NotificationerMock getNotificationerMock() {
        return notificationerMock;
    }

    public ServerCreatedSocket getServerCreatedSocket() {
        return serverCreatedSocket;
    }

    public void setDataTradeModels(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }
}
