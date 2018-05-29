package socket_installer.SI_parts.exception.server.general;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;


public class ServerGeneralException {

    public ServerGeneralException(){
    }

    public boolean handleGeneralException(Exception exception, SocketModel socketModel) {
        try{
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
        server.getSocketConfiguration().setSocketOnlineStatus(false);
    }
}
