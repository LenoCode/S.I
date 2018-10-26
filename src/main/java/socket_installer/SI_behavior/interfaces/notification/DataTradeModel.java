package socket_installer.SI_behavior.interfaces.notification;

import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.notification.data_trade.data_observers.DataObservers;

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
     void sendSignal(String signal) throws IOException, SocketExceptions;
     char waitForSignal() throws IOException, SocketExceptions;
     String waitForLongSignal(int signalSize) throws IOException, SocketExceptions;
     String waitForLongSignal(byte[] signalBuffer) throws IOException, SocketExceptions;
     DataObservers initDataObservers(Long...ids);
     void asyncDataListen(DataAsyncHandler dataAsyncHandler) throws IOException, SocketExceptions;
     ExternalContext getExternalContext();


}
