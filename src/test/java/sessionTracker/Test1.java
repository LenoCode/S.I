package sessionTracker;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.session_tracker.server.SessionTracker;

import java.io.IOException;

public class Test1 {


    public static void main(String[] args){

        SessionTracker a = new SessionTracker();

        SocketModel s = new SocketModel() {
            @Override
            public void activateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void deactivateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public SocketConfiguration getSocketConfiguration() {
                return null;
            }
        };

        SocketModel b = new SocketModel() {
            @Override
            public void activateSocket() throws IOException, SocketExceptions {

            }

            @Override
            public void deactivateSocket() throws IOException, SocketExceptions {

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
