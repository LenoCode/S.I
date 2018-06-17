package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.client_notificationer_action;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;

import java.io.IOException;

public abstract class ClientNotificationerActions <A extends DataTradeModel> extends NotificationerActions {
    protected ClientNotificationerActions(DataTradeModel[] objects) {
        super(objects);
    }

    @Override
    public void exceptionHandler(ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        if (lastMethodCalled.equals("No method called")){
            clientSocket.deactivateSocket();
        }else{
            A object = (A)annotationParser.identifyClass(objects,lastMethodCalled);
            if (!object.exceptionHandler(clientSocket,notificationerStatesBundle)){
                readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
            }
        }
    }
}
