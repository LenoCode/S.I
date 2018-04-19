package S.I_behavior.non_abstract_classes.session_tracker.server;

import S.I.server.socket.connected_client.ConnectedClient;
import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;


public class SessionTracker {

    private final ConnectedClientSet connectedClientSet;

    public SessionTracker(){
        connectedClientSet = new ConnectedClientSet();
    }

    public void addNewConnection (SocketModel socket){
        connectedClientSet.add(socket);
    }
    public void removeConnection(SocketModel socket){
        connectedClientSet.remove(socket);
    }
    public int getNumberOfActiveConnections(){
        return connectedClientSet.size();
    }

    @Override
    public String toString() {
        String template = "Connected clients : \n %s \n Number of active sockets: %s\n";
        return String.format(template,connectedClientSet.toString(),connectedClientSet.size());
    }
}
