package socket_installer.SI.server.socket_actions.socket_loop;

import socket_installer.SI_parts.exception.server.general.ServerGeneralException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

import java.io.IOException;


public class ServerWrappedLoop extends ProgramLoopWrapper {


    @Override
    public void activateWrappedLoop(SocketModel socketModel) throws NoSolutionForException {
        ServerGeneralException serverGeneralException = new ServerGeneralException();

        while( isProgramRunning()){
            try{
                socketModel.activateSocket();
            }catch (NoSolutionForException noSolutionException){
                throw noSolutionException;
            } catch (SocketExceptions socketExceptions) {
                socketExceptions.handleException(socketModel);
            }catch (IOException ioException){
                serverGeneralException.handleGeneralException(ioException,socketModel);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
