package test_server;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.CreatedSocket;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;


import java.io.IOException;

public class Test0 {

    public static void main(String[] args){

        final CreatedSocket server= ServerCreator.createServer("192.12131.2131", new Notificationer() {

            @Override
            public void notificationRecv(String notification) {
                System.out.println("NOTIFICATION :    "+notification);
            }
        }, 3000, 2, 40);

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
