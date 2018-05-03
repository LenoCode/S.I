package socket_installer.SI_parts.session_tracker.server;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.util.ArrayList;

class ConnectedClientSet extends ArrayList<ClientSocket> {


    public ClientSocket contains(String ipAddress) {
        for (ClientSocket socket:this ){
            if (socket.equals(ipAddress)){
                return socket;
            }
        }
        return null;
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
