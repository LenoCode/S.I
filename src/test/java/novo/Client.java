package novo;

import junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsClient;
import junit.tests.internal_tests.data_transfer.notificationer_mocks.ClientNotificationer;
import org.mockito.Mock;
import socket_installer.SI.socket_creation.client.ClientSocketCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;

import java.io.IOException;
import java.net.Socket;

import static junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethodsServer.CLASS_IDENT;
import static junit.tests.statics.static_fields.StaticFields.HOST;
import static junit.tests.statics.static_fields.StaticFields.PORT;
import static junit.tests.statics.static_fields.StaticFields.TIMEOUT;
import static junit.tests.statics.static_methods.StaticMethods.sleep;
import static junit.tests.statics.static_methods.StaticMethods.threadRun;
import static org.assertj.core.api.Assertions.assertThat;

public class Client {

    private ClientCreatedSocket clientCreatedSocket;
    @Mock
    private ClientNotificationer notificationerMock;

    private DataTradeModel[] dataTradeModels;

    public Client(DataTradeModel[] dataTradeModels) {
        this.dataTradeModels = dataTradeModels;
    }


    protected void before() throws Throwable {
        Socket socket = new Socket(HOST,PORT);


        notificationerMock = new ClientNotificationer(dataTradeModels);
        clientCreatedSocket = ClientSocketCreator.createClientCreatedSocket(notificationerMock,socket,TIMEOUT);
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

    public void send(String data) throws IOException, SocketExceptions {
        notificationerMock.sendNotification(CLASS_IDENT,"test01",data);

    }

    private void clientReadResponse() throws IOException, SocketExceptions {
       clientCreatedSocket.getClient().activateSocket();
    }


    public static void main(String[] args){
        Client client = new Client(new DataTradeModel[]{new NotificationTestMethodsClient()});
        try {
            client.before();
            System.out.println(client.clientCreatedSocket.getClient());
            client.send("adad");
            client.send("sadkjsaldkalkdačlk");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
