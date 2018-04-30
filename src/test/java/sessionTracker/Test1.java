package sessionTracker;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import java.io.IOException;

public class Test1 {


    public static void main(String[] args){

        SessionTracker a = new SessionTracker();

        BasicSocket s = new BasicSocket() {
            @Override
            public void activateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void deactivateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void setupSocket() throws IOException {

            }

            @Override
            public SocketConfiguration getSocketConfiguration() {
                return null;
            }
        };

        BasicSocket b = new BasicSocket() {
            @Override
            public void activateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void deactivateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void setupSocket() throws IOException {

            }

            @Override
            public SocketConfiguration getSocketConfiguration() {
                return null;
            }
        };
        a.addNewConnection(b);
        a.addNewConnection(s);

        System.out.println(a);


        a.removeConnection(b);

        System.out.println(a);
    }
}
