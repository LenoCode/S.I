package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder.NotificationerObjects;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_behavior.interfaces.notification.NotificationerActionsModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.main_processors.ClientMainProcessor;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class NotificationerActions <A extends DataTradeModel> extends NotificationerObjects implements NotificationerActionsModel {

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

    public void resetNotificationer(){
        notificationerStatesBundle.closeStream();
    }

    public void notifyClass(String notification) throws IOException, SocketExceptions {
        saveLastMethodForExceptionHandle(notification);

        A object = (A)annotationParser.identifyClass(objects,notification);
        Method method = annotationParser.identifyMethod(object.getClass().getMethods(),notification);
        notification = annotationParser.removeIdents(notification);
        invokeMethod(object,method,notification,notificationerStatesBundle);
    }
    public void sendNotification(String classIdent,String methodIdent,String notification) {
        try {
            System.out.println("SALJEM NOTIFIAITON "+notification);
            ClientMainProcessor communicationProcessor = CommunicationProcessor.getClientCommunicationProcessor();
            communicationProcessor.openStreamSocket(clientSocket);
            communicationProcessor.sendNotification(clientSocket,classIdent,methodIdent,notification);
            communicationProcessor.readingDataFromStream(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }
    }

    private void invokeMethod(A object,Method method, Object ... args) throws SocketExceptions, IOException {
        try {
            method.invoke(object,args);
            closeStream();
        } catch (IllegalAccessException e) {
            lastMethodCalled = "No method called";
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke illegalAccessException");
        } catch (InvocationTargetException e) {
            lastMethodCalled = "No method called";
            e.printStackTrace();
            throw new NoSolutionForException("Internal error : method invoke invocationTargetException");
        }
    }
    private void saveLastMethodForExceptionHandle(String lastMethod){
        this.lastMethodCalled = lastMethod;
    }

    public void closeStream() throws IOException, SocketExceptions {
        CommunicationProcessor.MainProcessor().sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol().getBytes());
    }

}
