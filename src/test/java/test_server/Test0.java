package test_server;

import annotations.Notificationer;
import socket_installer.SI.socket_creation.server.ServerCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;


import java.io.IOException;






public class Test0 {

    public static void main(String[] args){
        Methods[] m = {new Methods()};
        Notificationer notificationer = new Notificationer(m);
        final ServerCreatedSocket server= ServerCreator.createServer("192.168.5.17",notificationer, 3000, 2, 1);

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
