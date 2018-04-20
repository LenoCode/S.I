package socket_installer.SI.server.socket_exception;

import socket_installer.SI.server.socket.Server;
import socket_installer.SI.server.socket.ServerConfiguration;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

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
