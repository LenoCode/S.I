package S.I.server.socket_actions.server_loop;

import S.I.server.socket_exception.ConnectedClientGeneralException;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketModel;

import java.io.IOException;

public class ConnectedClientWrappedLoop extends ProgramLoopWrapper {


    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        ConnectedClientGeneralException connectedClientGeneralException = new ConnectedClientGeneralException();

        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
           try{
               socketModel.activateSocket();
           }catch (SocketExceptions socketExceptions){
               System.out.println("tu sam");
               socketExceptions.handleException(socketModel);
           } catch (IOException ioException){
               connectedClientGeneralException.handleGeneralException(ioException,socketModel);
           }
        }
    }

}
