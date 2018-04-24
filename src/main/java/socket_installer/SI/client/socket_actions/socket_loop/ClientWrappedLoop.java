package socket_installer.SI.client.socket_actions.socket_loop;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket_exception.ClientGeneralException;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;

import java.io.IOException;

public class ClientWrappedLoop extends ProgramLoopWrapper {

    @Override
    public void activateWrappedLoop(SocketModel socketModel) {
        ClientGeneralException clientGeneralException = new ClientGeneralException();

        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
            try{
                socketModel.activateSocket();
            }catch (SocketExceptions socketExceptions){
                socketExceptions.handleException(socketModel);
            } catch (IOException ioException){
                clientGeneralException.handleGeneralException(ioException,socketModel);
            }
        }
        System.out.println("Should socket be removed :"  +((ClientConfiguration)socketModel.getSocketConfiguration()).shouldClientBeRemoved());
    }
}
