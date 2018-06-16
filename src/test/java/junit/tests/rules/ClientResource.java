package junit.tests.rules;


import junit.tests.client.client_communication.mock_objects.NotificationerMethodsMock;
import junit.tests.client.client_communication.mock_objects.NotificationerMock;
import org.junit.rules.ExternalResource;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import static junit.tests.statics.static_fields.StaticFields.*;
import static junit.tests.statics.static_methods.StaticMethods.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.Socket;

public class ClientResource extends ExternalResource {

    private ClientCreatedSocket clientCreatedSocket;
    @Mock
    private NotificationerMock notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public ClientResource(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }

    @Override
    protected void before() throws Throwable {
        Socket socket = new Socket(HOST,PORT);
        notificationerMock = new NotificationerMock(dataTradeModels);
        clientCreatedSocket = ClientCreator.createClient(notificationerMock,socket,TIMEOUT);
        threadRun(new Runnable() {
            @Override
            public void run() {
                try {
                    clientCreatedSocket.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        });
        sleep(2000);
        System.out.println("Connected to server");
    }


    @Override
    protected void after() {
        clientCreatedSocket.closeProgram();
        sleep(10000);
        assertThat(clientCreatedSocket.isConnectedToServer()).isEqualTo(false);
        System.out.println("Client disconnected");
    }


    public ClientCreatedSocket getClientCreatedSocket() {
        return clientCreatedSocket;
    }

    public NotificationerMock getNotificationerMock() {
        return notificationerMock;
    }

    public void setDataTradeModels(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }
}
