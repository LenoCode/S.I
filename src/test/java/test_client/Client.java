package test_client;

import socket_installer.SI_parts.protocol.CommunicationProtocol;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {


    public static void main(String[] args){
        try {
            CommunicationProtocol socketCommunicationProtocol = new CommunicationProtocol();
            Socket socket = new Socket("172.20.2.24",3000);
            socket.setSoTimeout(2000);
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());


            outputStream.write(socketCommunicationProtocol.implementSentClientProtocol("Ovo ti je message a ono je samo protokol"));
            outputStream.write(socketCommunicationProtocol.implementSentClientProtocol("Novi message a sta ces sad"));
            outputStream.flush();

            Thread.sleep(1000);

            if (inputStream.available() == 0){
                socket.close();
                System.out.println("Novo otvaram");
                Socket noviSocket = new Socket("172.20.2.229",3000);
                BufferedOutputStream soutputStream = new BufferedOutputStream(noviSocket.getOutputStream());
                BufferedInputStream sinputStream = new BufferedInputStream(noviSocket.getInputStream());

                soutputStream.write("ovo je novi message".getBytes());
                soutputStream.flush();
            }


            System.out.println("Client conneccting");

            System.out.println("Kraj");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
