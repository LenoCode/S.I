package junit.tests.internal_tests.data_transfer;

import junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethods;
import junit.tests.rules.ClientResource;
import junit.tests.rules.ServerResource;
import junit.tests.statics.static_objects.thread_communicators.ThreadCounterCommunicator;
import org.junit.Rule;
import org.junit.Test;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;
import static org.assertj.core.api.Assertions.*;


import java.io.IOException;

import static junit.tests.internal_tests.data_transfer.notification_test_methods.NotificationTestMethods.CLASS_IDENT;


public class DataTransferTests {

    @Rule
    public ServerResource serverResource = new ServerResource(new DataTradeModel[]{(DataTradeModel) new NotificationTestMethods()});
    @Rule
    public ClientResource clientResource = new ClientResource( new DataTradeModel[]{(DataTradeModel) new NotificationTestMethods()} );

    //THREAD COMMUNICATORS
    private final ThreadCounterCommunicator threadCounterCommunicator = ThreadCounterCommunicator.getThreadCounterCommunicator();


    @Test
    public void testIfDataIsSendToServer() throws IOException, SocketExceptions {
        PacketHolder packetHolder = createPacketHolderClient();
        packetHolder.setData(ClientProtocol.sendMessageFormat(CLASS_IDENT,"test01","testingMessage"));
        PacketProcessor.getPacketProcessor(clientResource.getClientCreatedSocket().getClient()).sendPacket(packetHolder);
    }

    @Test
    public void checkIfClientGetResponseFromServerInRangeOf5sec() throws IOException, SocketExceptions {
        PacketHolder packetHolder = createPacketHolderClient();
        packetHolder.setData(ClientProtocol.sendMessageFormat(CLASS_IDENT,"test02_server","waiting response"));
        PacketProcessor.getPacketProcessor(clientResource.getClientCreatedSocket().getClient()).sendPacket(packetHolder);
        clientReadResponse();
    }

    @Test()
    public void checkIfClientAndServerCanCommunicateSomePeriodOfTime() throws IOException, SocketExceptions {
        PacketHolder packetHolder = createPacketHolderClient();
        packetHolder.setData(ClientProtocol.sendMessageFormat(CLASS_IDENT,"test03_server","message count:"+threadCounterCommunicator.getCounter()));
        PacketProcessor.getPacketProcessor(clientResource.getClientCreatedSocket().getClient()).sendPacket(packetHolder);
        while(threadCounterCommunicator.getCounter() < 100000){
            clientReadResponse();
        }
        assertThat(threadCounterCommunicator.getCounter()).isEqualTo(100000);
        threadCounterCommunicator.finish();
        System.out.println("Server and client exchanged 100000 messages");
    }

    @Test
    public void checkIfClientAndServerCanCommunicateSomePeriodOfTimeWithClosingConnection() throws IOException, SocketExceptions {
        PacketHolder packetHolder = createPacketHolderClient();
        packetHolder.setData(ClientProtocol.sendMessageFormat(CLASS_IDENT,"test04_server","message count:"+threadCounterCommunicator.getCounter()));
        PacketProcessor.getPacketProcessor(clientResource.getClientCreatedSocket().getClient()).sendPacket(packetHolder);
        while(threadCounterCommunicator.getCounter() < 10000){
            clientReadResponse();
        }
        assertThat(threadCounterCommunicator.getCounter()).isEqualTo(10000);
        threadCounterCommunicator.finish();
        System.out.println("Server and client exchanged 100000 messages with closing connection");
    }










    //Private methods

    private PacketHolder createPacketHolderClient(){
        return new PacketHolder(clientResource.getClientCreatedSocket().getClient());
    }
    private PacketHolder createPacketHolderServer(){
        return new PacketHolder(clientResource.getClientCreatedSocket().getClient());
    }
    private void clientReadResponse() throws IOException, SocketExceptions {
        clientResource.getClientCreatedSocket().getClient().activateSocket();
    }
}
