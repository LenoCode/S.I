package socket_implementing;

import S.I.socket_creation.server.ServerCreator;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;

public class Test0 {

    public static void main(String[] args){

        final CreatedSocketModel server= ServerCreator.createServer("192.12131.2131",3000,2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                server.runSocket();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Closing program");
        server.closeProgram();
    }
}
