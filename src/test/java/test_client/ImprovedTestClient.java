package test_client;

import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;


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
                if (socket.sendMessageToServer(line)) {
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
        Socket socket = new Socket("192.168.5.17",3000);
        socket.setSoTimeout(10);

        ClientCreatedSocket createdSocket = ClientCreator.createClient(null , socket);

        return createdSocket;
    }
}


