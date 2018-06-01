package junit.tests.server.server_communication;


import junit.tests.rules.ServerResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.server.ServerCreatedSocket;
import socket_installer.SI_context.internal_context.InternalContext;
import socket_installer.SI_parts.session_tracker.server.SessionTracker;

import static junit.tests.statics.static_methods.StaticMethods.*;
import static org.assertj.core.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class ServerCommunication {

    @Rule
    public ServerResource serverResource = new ServerResource(null);


    @Test
    public void testIsServerOnline(){
        ServerCreatedSocket serverCreatedSocket = serverResource.getServerCreatedSocket();
        assertThat(serverCreatedSocket.isServerRunning()).isEqualTo(true);
    }

    @Test
    public void testIfClientCanConnectToServer() throws Exception {
        int waitLong = 10;
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();
        while((waitLong = waitTurn(waitLong)) > 0){
                if (sessionTracker.getNumberOfActiveConnections() > 0){
                    assertThat(sessionTracker.getNumberOfActiveConnections()).isEqualTo(1);
                    return;
                }
        }
        throw new Exception("No client connected");
    }
    @Test
    public void testIfClientIsRemovedFromSessionTracker() throws Exception {
        int waitLong = 10;
        SessionTracker sessionTracker = (SessionTracker) InternalContext.getInternalContext().getContextObject("SessionTracker").getObject();

        while((waitLong = waitTurn(waitLong)) > 0){
            if ( sessionTracker.getNumberOfActiveConnections() > 0 ){
                assertThat( sessionTracker.getNumberOfActiveConnections() ).isEqualTo(1);
                sleep(5000);
                assertThat(sessionTracker.getNumberOfActiveConnections()).isEqualTo(0);
                return;
            }
        }
        throw new Exception(" No client connected ");
    }

}
