package socket_installer.SI_parts.session_tracker.server;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.util.ArrayList;

class ConnectedClientSet extends ArrayList<BasicSocket> {


    public ConnectedClientSet(){
    }

    @Override
    public boolean add(BasicSocket basicSocket) {
        if (!super.contains(basicSocket)) {
            return super.add(basicSocket);
        }
        return true;
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
