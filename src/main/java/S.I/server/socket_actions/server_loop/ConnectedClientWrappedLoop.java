package S.I.server.socket_actions.server_loop;

import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ErrorWrappedLoop;
import S.I_behavior.interfaces.sockets.SocketModel;

public class ConnectedClientWrappedLoop extends ErrorWrappedLoop {
    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
            System.out.println("Client connected");
        }
    }

    @Override
    public void callGeneralException(Exception e, SocketModel socketModel) {

    }
}
