package socket_installer.SI_behavior.interfaces.notification;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;

import java.io.IOException;

public interface NotificationerActionsModel {
    void exceptionHandler(ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions;
    void callExternalInitializator();
}
