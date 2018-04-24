package test_client;

import socket_installer.SI_parts.protocol.BasicSocketCommunicationProtocol;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {



    public static void main(String[] args){
        try {
            BasicSocketCommunicationProtocol socketCommunicationProtocol = new BasicSocketCommunicationProtocol();
            Socket socket = new Socket("172.20.2.161",3000);
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            outputStream.write(socketCommunicationProtocol.implementSentProtocol("Ovo ti je message a ono je samo protokol"));
            outputStream.flush();
            System.out.println("Client conneccting");

            Thread.sleep(30);
            outputStream.write("Ovo je druga poruka koja se mora ispisati".getBytes());
            outputStream.flush();
            Thread.sleep(6000);
            System.out.println("Kraj");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
