package S.I.server.socket_exception;

import S.I.server.socket.Server;
import S.I.server.socket.ServerConfiguration;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketModel;

import java.io.IOException;


public class ServerGeneralException {

    public ServerGeneralException(){
    }

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        try{
            System.out.println("Error "+exception.getMessage());
            Server server = (Server) socketModel;
            setServerToOffline(server);
            server.deactivateSocket();
        }catch (SocketExceptions e){
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    private void setServerToOffline(Server server){
        ((ServerConfiguration)server.getSocketConfiguration()).setSocketOnlineStatus(false);
    }
}
