package socket_installer.SI_parts.session_tracker.server;

import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

import java.util.HashSet;

class ConnectedClientSet extends HashSet<SocketModel> {


    public ConnectedClientSet(){
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
