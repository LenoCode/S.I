package socket_installer.SI_parts.session_tracker.server;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;


public class SessionTracker {

    private final ConnectedClientSet connectedClientSet;

    public SessionTracker(){
        connectedClientSet = new ConnectedClientSet();
    }

    public void addNewConnection (BasicSocket socket){
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
