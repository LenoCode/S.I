package S.I.server.socket_actions.server_loop;

import S.I.server.socket_exception.ServerGeneralException;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketModel;

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
