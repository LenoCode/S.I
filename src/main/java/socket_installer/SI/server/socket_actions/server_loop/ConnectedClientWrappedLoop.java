package socket_installer.SI.server.socket_actions.server_loop;

import socket_installer.SI.server.socket_exception.ConnectedClientGeneralException;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.SocketModel;

import java.io.IOException;

public class ConnectedClientWrappedLoop extends ProgramLoopWrapper {



    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        ConnectedClientGeneralException connectedClientGeneralException = new ConnectedClientGeneralException();

        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
           try{
               socketModel.activateSocket();
           }catch (SocketExceptions socketExceptions){
               socketExceptions.handleException(socketModel);
           } catch (IOException ioException){
               connectedClientGeneralException.handleGeneralException(ioException,socketModel);
           }
        }
    }

}
