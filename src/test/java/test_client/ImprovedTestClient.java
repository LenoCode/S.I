package test_client;

import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer.Notificationer;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.NotificationerModel;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ImprovedTestClient {


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        ClientCreatedSocket socket = null;
        try {
            socket = start();
            socket.runSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }

        String line;
        while(!(line = scanner.nextLine()).equals("Exit")){
            for (int i=0; i<1; ++i) {
                if (socket.sendMessageToServer(line+"- number of iteration  "+i)) {
                    socket.activateSocket();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Poruka nije poslana");
                }
            }
        }
        try {
            socket.getClient().deactivateSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }

    }



    public static ClientCreatedSocket start()throws IOException {
        Socket socket = new Socket("192.168.5.13",3000);
        socket.setSoTimeout(10);
        ClientCreatedSocket createdSocket = ClientCreator.createClient(new Notificationer() {
            @Override
            public void notificationRecv(String notification) {
                System.out.println("NOTIFICATION :    "+notification);
            }
        }, socket);

        return createdSocket;



    }
}
