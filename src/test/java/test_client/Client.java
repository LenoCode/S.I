package test_client;

import java.io.IOException;
import java.net.Socket;

public class Client {



    public static void main(String[] args){
        try {
            Socket socket = new Socket("172.20.2.161",3000);
            System.out.println("Client conneccting");
            Thread.sleep(60000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
