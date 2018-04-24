package test_client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {



    public static void main(String[] args){
        try {
            Socket socket = new Socket("172.20.2.161",3000);
            BufferedOutputStream outputStream = new BufferedOutputStream(socket.getOutputStream());
            outputStream.write("king kong je tu a disi ti ".getBytes());
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
