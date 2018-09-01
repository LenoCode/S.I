package socket_installer_test_environment.rules.socket_rules;


import org.junit.rules.ExternalResource;
import org.mockito.Mock;
import socket_installer.SI.socket_creation.client.ClientSocketCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer_test_environment.socket_parts.notificationers.ClientNotificationer;


import static org.assertj.core.api.Assertions.*;
import static socket_installer_test_environment.tools.static_fields.StaticFields.HOST;
import static socket_installer_test_environment.tools.static_fields.StaticFields.PORT;
import static socket_installer_test_environment.tools.static_fields.StaticFields.TIMEOUT;
import static socket_installer_test_environment.tools.static_methods.StaticMethods.sleep;
import static socket_installer_test_environment.tools.static_methods.StaticMethods.threadRun;


import java.io.IOException;
import java.net.Socket;

public class ClientResource extends ExternalResource {

    private ClientCreatedSocket clientCreatedSocket;
    @Mock
    private ClientNotificationer notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public ClientResource(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }

    @Override
    protected void before() throws Throwable {
        Socket socket = new Socket(HOST,PORT);
        notificationerMock = new ClientNotificationer(dataTradeModels);
        clientCreatedSocket = ClientSocketCreator.createClientCreatedSocket(notificationerMock,socket,TIMEOUT);
        threadRun(new Runnable() {
            @Override
            public void run() {
                try {
                    clientCreatedSocket.initSocket();
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

    public ClientNotificationer getNotificationerMock() {
        return notificationerMock;
    }

    public void setDataTradeModels(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }
}
