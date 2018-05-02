package test_client;

import socket_installer.SI_parts.protocol.CommunicationProtocol;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {


    public static void main(String[] args){
        try {
            CommunicationProtocol socketCommunicationProtocol = new CommunicationProtocol();
            Socket socket = new Socket("172.20.2.24",3000);
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());

            Thread.sleep(10000);

            outputStream.write(socketCommunicationProtocol.implementSentProtocol("Ovo ti je message a ono je samo protokol"));
            outputStream.write(socketCommunicationProtocol.implementSentProtocol("Novi message a sta ces sad"));
            outputStream.flush();
            System.out.println("Client conneccting");


            System.out.println("Kraj");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
