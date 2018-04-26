package test_server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.CreatedSocket;
import socket_installer.SI_behavior.interfaces.io_observer.notification_handler.NotificationHandler;


import java.io.IOException;

public class Test0 {

    public static void main(String[] args){

        final CreatedSocket<Server> server= ServerCreator.createServer("192.12131.2131", new NotificationHandler() {
            @Override
            public void handleNotification(String notification) {
                System.out.println("ovo je seuper :            "+notification);
            }
        }, 3000, 2, 40);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    server.runSocket();
                } catch (IOException e) {
                    e.printStackTrace();
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
