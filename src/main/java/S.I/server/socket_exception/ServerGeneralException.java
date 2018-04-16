package S.I.server.socket_exception;

import S.I.server.socket.Server;
import S.I.server.socket.ServerConfiguration;
import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;
import S.I_behavior.interfaces.exceptions.GeneralExceptionModel;
import S.I_behavior.interfaces.sockets.SocketModel;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;


public class ServerGeneralException implements GeneralExceptionModel {

    public ServerGeneralException(){
    }
    @Override
    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        try{
            System.out.println("Error "+exception.getMessage());
            Server server = (Server) socketModel;
            setServerToOffline(server);
            server.deactivateSocket();
        }catch (IOException e){
            return false;
        }catch (AbstractSpecificException e){
            return false;
        }
        return true;
    }
    private void setServerToOffline(Server server){
        ((ServerConfiguration)server.getSocketConfiguration()).setServerOnlineStatus(false);
    }
}
