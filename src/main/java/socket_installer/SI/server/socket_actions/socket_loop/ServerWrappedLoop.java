package socket_installer.SI.server.socket_actions.socket_loop;

import socket_installer.SI.server.socket_exception.ServerGeneralException;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;


public class ServerWrappedLoop extends ProgramLoopWrapper {


    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        ServerGeneralException serverGeneralException = new ServerGeneralException();

        while( isProgramRunning()){
            try{
                socketModel.activateSocket();
            } catch (SocketExceptions socketExceptions) {
                socketExceptions.handleException(socketModel);
            }catch (IOException ioException){
                serverGeneralException.handleGeneralException(ioException,socketModel);
            }
        }
    }
}
