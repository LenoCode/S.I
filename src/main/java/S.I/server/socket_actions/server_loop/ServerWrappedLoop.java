package S.I.server.socket_actions.server_loop;

import S.I.server.socket_exception.ServerGeneralException;
import S.I_behavior.abstractClasses.exceptions.AbstractSpecificException;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ErrorWrappedLoop;
import S.I_behavior.interfaces.sockets.SocketModel;

import java.io.IOException;


public class ServerWrappedLoop extends ErrorWrappedLoop {
    private ServerGeneralException serverGeneralException;

    public ServerWrappedLoop(){
        this.serverGeneralException = new ServerGeneralException();
    }

    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        while( isProgramRunning()){
            try{
                socketModel.activateSocket();
            }catch (AbstractSpecificException e) {
                e.printStackTrace();
            } catch (IOException e) {
                serverGeneralException.handleGeneralException(e,socketModel);
            }
        }
    }

    @Override
    public void callGeneralException(Exception e, SocketModel socketModel) {
        if (!serverGeneralException.handleGeneralException(e,socketModel)){
            ErrorWrappedLoop.setProgrammRunning(false);
        }
    }
}
