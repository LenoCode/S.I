package socket_installer.SI_behavior.interfaces.notification.annotation_parser.method_annotater.method_steps;



import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;
import java.lang.reflect.Method;

public interface MethodSteps {
    <A>void runStep(ClientSocket clientSocket, A object, Method method, String data) throws IOException, SocketExceptions;
}
