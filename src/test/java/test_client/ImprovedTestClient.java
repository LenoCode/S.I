package test_client;

import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;


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
            socket.sendMessageToServer(line);
            socket.activateSocket();
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
        Socket socket = new Socket("172.20.2.229",3000);
        socket.setSoTimeout(50000);
        ClientCreatedSocket createdSocket = ClientCreator.createClient(new Notificationer() {
            @Override
            public void notificationRecv(String notification) {
                System.out.println("Ovo je notification :   "+notification);
            }
        }, socket);

        return createdSocket;



    }
}
