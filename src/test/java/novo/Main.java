package novo;

import socket_installer.SI_behavior.annotations.user_implementation.methods_implementation.methods_annotation.method_identifier.ExceptionHandleMethod;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private int a;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    main.startServerSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        main.client();

    }

    @ExceptionHandleMethod(identification = "staro")
    public void test(){

    }

    public void startServerSocket() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(3000);
        byte[] buffer = new byte[1024];
        int a= 0;
        while(true){
           Socket socket =  serverSocket.accept();
           if (a == 0) {
               Thread.sleep(5000);
               InputStream inputStream = socket.getInputStream();
               OutputStream outputStream = socket.getOutputStream();
               inputStream.read(buffer);
               System.out.println(new String(buffer));
               outputStream.write("sadada".getBytes());
               outputStream.flush();
               try{
               int k = inputStream.read();
               System.out.println(k);
               }catch (IOException e){
                   System.out.println(e.getMessage());
               }
               break;
           }
           ++a;
        }
    }
    public void client() throws IOException {
        for (int i = 0; i < 2 ; i++) {
            Socket socket = new Socket("192.168.43.29", 3000);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("Test  "+i).getBytes());
            outputStream.flush();
            socket.close();
        }
        System.out.println("closed");
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
