package S.I_behavior.non_abstract_classes.session_tracker.server;

import S.I_behavior.interfaces.sockets.SocketConfiguration;
import S.I_behavior.interfaces.sockets.SocketModel;

import java.util.HashSet;
import java.util.Set;

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
