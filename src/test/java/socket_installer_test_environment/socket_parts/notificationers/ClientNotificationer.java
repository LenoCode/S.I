package socket_installer_test_environment.socket_parts.notificationers;

import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.client_notificationer_action.ClientNotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;


import java.io.IOException;

public class ClientNotificationer extends ClientNotificationerActions {
    public ClientNotificationer(DataTradeModel[] objects) {
        super(objects);
        setupExternalContextInitializator(new ExternalContextInitializator() {
            @Override
            public void initializeExternalContext(ExternalContext externalContext) {

            }
        });
    }

    @Override
    public void exceptionHandler(ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {

    }
}
