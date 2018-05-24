package test_server;

import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_parts.notification.Notificationer;


import java.io.IOException;




class Notification extends Notificationer {


    protected Notification(Methods[] methods) {
        super(methods);
    }
}

public class Test0 {

    public static void main(String[] args){
        Methods[] m = {new Methods()};
        Notification s = new Notification(m);
        final ServerCreatedSocket server= ServerCreator.createServer("192.168.43.226",s, 3000, 2, 1);

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
