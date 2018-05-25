package test_client;

import annotations.Notificationer;
import socket_installer.SI.socket_creation.client.ClientCreator;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ImprovedTestClient {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        ClientCreatedSocket socket = null;
        Methods m = new Methods();
        try {
            socket = start(m);
            socket.runSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SocketExceptions socketExceptions) {
            socketExceptions.printStackTrace();
        }

        String line;
        while(!(line = scanner.nextLine()).equals("Exit")){
            for (int i=0; i<1; ++i) {
                m.sendMessage(line);
                m.receive();
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



    public static ClientCreatedSocket start(Methods m)throws IOException {
        Socket socket = new Socket("192.168.5.17",3000);
        socket.setSoTimeout(10);
        test_client.Notificationer a = new test_client.Notificationer(new DataTradeModel[]{m});
        ClientCreatedSocket createdSocket = ClientCreator.createClient(a, socket);

        return createdSocket;
    }
}


