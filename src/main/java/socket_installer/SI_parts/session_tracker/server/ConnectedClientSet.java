package socket_installer.SI_parts.session_tracker.server;

import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.util.ArrayList;

class ConnectedClientSet extends ArrayList<ConnectedClient> {


    public ConnectedClient contains(String ipAddress) {
        for (ConnectedClient socket:this ){
            if (socket.equals(ipAddress)){
                return socket;
            }
        }
        return null;
    }
    public boolean removeConnection(SocketModel socketModel) {
        ConnectedClient connectedClient = (ConnectedClient) socketModel;
        String ipAddress = connectedClient.getSocketConfiguration().getIpAddress();
        for (ConnectedClient socket : this){
            if (socket.equals(ipAddress)){
                return super.remove(connectedClient);
            }
        }
        return false;
    }
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        int count=0;
        for (SocketModel socket : this){
            buffer.append(Integer.toString(count));
            buffer.append("  ");
            buffer.append(socket.toString());
            buffer.append("\n");
            ++count;
        }
        return buffer.toString();
    }




}
