package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder.NotificationerObjects;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.IO.communication_processor_test_2.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor_test_2.main_processors.ClientMainProcessor;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;
import socket_installer.SI_parts.protocol.enum_protocols.data_protocol.DataProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public abstract class NotificationerActions <A extends DataTradeModel> extends NotificationerObjects {

    protected NotificationerActions(A[] objects) {
        super(objects);
    }

    public void createExternalContext(ExternalContextInitializator externalContextInitializator){
        externalContext = new ExternalContext();
        externalContextInitializator.initializeExternalContext(externalContext);
    }
    public ExternalContext getExternalContext(){
        return externalContext;
    }


    public void notifyClass(String notification) throws IOException, NoSolutionForException {
        A object = (A)annotationParser.identifyClass(objects,notification);
        Method method = annotationParser.identifyMethod(object.getClass().getMethods(),notification);
        notification = annotationParser.removeIdents(notification);
        invokeMethod(object,method,notification);
    }
    public void sendNotification(String classIdent,String methodIdent,String notification) {
        try {
            ClientMainProcessor communicationProcessor = CommunicationProcessor.getClientCommunicationProcessor();
            communicationProcessor.openStreamSocket(clientSocket);
            communicationProcessor.sendNotification(clientSocket,DataProtocol.sendMessageFormat(classIdent,methodIdent,notification));
            communicationProcessor.sendNotification(clientSocket,EndMarkerProtocol.END_TRANSFER.getProtocol());
            communicationProcessor.readingDataFromStream(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }

    private void invokeMethod(A object,Method method, Object ... args) throws NoSolutionForException {
        try {
            method.invoke(object,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke illegalAccessException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke invocationTargetException");
        }
    }
}
