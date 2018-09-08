package socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions;

import socket_installer.SI_behavior.abstractClasses.notification.notification_object_holder.NotificationerObjects;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.MethodIdentifier;
import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.StreamOpen;
import socket_installer.SI_behavior.interfaces.context.ExternalContextInitializator;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_behavior.interfaces.notification.NotificationerActionsModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class NotificationerActions <A extends DataTradeModel> extends NotificationerObjects implements NotificationerActionsModel {
    private ExternalContextInitializator externalContextInitializator;

    protected NotificationerActions(A[] objects) {
        super(objects);
    }

    public void setupExternalContextInitializator(ExternalContextInitializator externalContextInitializator){
        this.externalContextInitializator = externalContextInitializator;
    }

    @Override
    public void callExternalInitializator() {
        externalContext = new ExternalContext();
        externalContextInitializator.initializeExternalContext(externalContext);
    }

    public ExternalContext getExternalContext(){
        return externalContext;
    }

    public void notifyClass(String notification) throws IOException, SocketExceptions {
        saveLastMethodForExceptionHandle(notification);

        A object = (A)annotationParser.identifyClass(objects,notification);
        Method method = annotationParser.identifyMethod(object.getClass().getMethods(),notification);
        notification = annotationParser.removeIdents(notification);
        invokeMethod(object,method,notification,notificationerStatesBundle);
    }


    private void invokeMethod(A object,Method method, Object ... args) throws SocketExceptions, IOException {
        try {
            method.invoke(object,args);
            closeStream(method);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            lastMethodCalled = "No method called";
        }
    }

    private void saveLastMethodForExceptionHandle(String lastMethod){
        this.lastMethodCalled = lastMethod;
    }

    private void closeStream(Method method) throws IOException, SocketExceptions {
        MethodIdentifier methodIdentifier = method.getAnnotation(MethodIdentifier.class);
        StreamOpen streamOpen = method.getAnnotation(StreamOpen.class);
        if (methodIdentifier.closeStream() && streamOpen == null){
            System.out.println("CLOSING STREAM");
            CommunicationProcessor.MainProcessor().sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol().getBytes());

        }
    }

}
