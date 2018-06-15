package socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.communication_processor_test_2.buffer_processor.BufferProcessor;
import socket_installer.SI_parts.IO.communication_processor_test_2.read_processor.ReadProcessor;
import socket_installer.SI_parts.IO.communication_processor_test_2.send_processor.SendProcessor;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;

import java.io.IOException;
import java.util.Iterator;

public abstract class MainProcessor {

    protected final SendProcessor sendProcessor;
    protected final ReadProcessor readProcessor;
    protected final BufferProcessor bufferProcessor;

    public MainProcessor(){
        sendProcessor = new SendProcessor();
        readProcessor = new ReadProcessor();
        bufferProcessor = new BufferProcessor();
    }

    public void notifyClass(NotificationerActions notificationerActions, StringBuffer stringBuffer) throws IOException, SocketExceptions {
        Iterator<String> iterator = bufferProcessor.parseDataRecieved(stringBuffer);

        stringBuffer.emptyBuffer();
        while(iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
            notificationerActions.notifyClass( bufferProcessor.extractNotification(next) );
        }
    }


}
