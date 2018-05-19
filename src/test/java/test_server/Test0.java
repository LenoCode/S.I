package test_server;

import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer.Notificationer;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.NotificationerModel;


import java.io.IOException;

public class Test0 {

    public static void main(String[] args){

        final ServerCreatedSocket server= ServerCreator.createServer("192.168.5.13", new Notificationer() {

            @Override
            public void notificationRecv(String notification)throws SocketExceptions, IOException {
                String response = "RESPONSE ON : "+notification;
                System.out.println("NOTIFICATION:   "+notification);
                sendMessage(response);


            }
        }, 3000, 2, 1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        }).start();

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Closing program");
        server.closeProgram();
        */
    }
}
