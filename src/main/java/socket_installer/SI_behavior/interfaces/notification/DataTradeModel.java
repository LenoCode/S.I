package socket_installer.SI_behavior.interfaces.notification;

import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.external_context.ExternalContext;

import java.io.IOException;


public interface DataTradeModel {
     void setClientSocket(ClientSocket clientSocket);
     ClientSocket getClientSocket();
     boolean exceptionHandler(ClientSocket clientSocket,NotificationerStatesBundle notificationerStatesBundle);
     void sendNotification(String classIdent, String methodIdent, String data) throws IOException, SocketExceptions;
     byte[] download() throws IOException,SocketExceptions;
     byte[] download(int bytesSize) throws IOException,SocketExceptions;
     void upload(byte[] bytes) throws IOException, SocketExceptions;
     void closeStream() throws IOException, SocketExceptions;
     void injectExternalContext(ExternalContext externalContext);
     void sendSizeOfDownload(int size) throws IOException,SocketExceptions;
     void resetExternalContext();
     void sendSignal(char signal) throws IOException, SocketExceptions;
     void sendSignalWithSeperator(char signal) throws IOException, SocketExceptions;
     void sendSignalWithSeperator(String signal) throws IOException, SocketExceptions;
     char waitForSignal() throws IOException, SocketExceptions;
     String waitForLongSignal(int signalSize) throws IOException, SocketExceptions;
     String waitForLongSignal(byte[] signalBuffer) throws IOException, SocketExceptions;
     String waitForLongSignal() throws IOException,SocketExceptions;
     String[] seperateSignal(String signal) throws IOException,SocketExceptions;
     ExternalContext getExternalContext();


}
