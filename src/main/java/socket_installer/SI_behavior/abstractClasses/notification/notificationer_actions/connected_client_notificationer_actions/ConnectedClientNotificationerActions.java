package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.connected_client_notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;

import java.io.IOException;

public abstract class ConnectedClientNotificationerActions <A extends DataTradeModel> extends NotificationerActions {
    protected ConnectedClientNotificationerActions(DataTradeModel[] objects) {
        super(objects);
    }

    @Override
    public void exceptionHandler(ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        readStatusProcessorModel.setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
    }
}
