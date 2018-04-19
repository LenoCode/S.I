package S.I.server.socket_actions.server_loop;

import S.I.server.socket_exception.ConnectedClientGeneralException;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.interfaces.sockets.SocketModel;
import S.I_behavior.non_abstract_classes.session_tracker.server.SessionTracker;

import java.io.IOException;

public class ConnectedClientWrappedLoop extends ProgramLoopWrapper {

    private SessionTracker sessionTracker;

    public void setSessionTracker(SessionTracker sessionTracker) {
        this.sessionTracker = sessionTracker;
    }

    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        ConnectedClientGeneralException connectedClientGeneralException = new ConnectedClientGeneralException();

        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
           try{
               socketModel.activateSocket();
           }catch (SocketExceptions socketExceptions){
               socketExceptions.handleException(socketModel,sessionTracker);
           } catch (IOException ioException){
               connectedClientGeneralException.handleGeneralException(ioException,socketModel);
           }
        }
    }

}
