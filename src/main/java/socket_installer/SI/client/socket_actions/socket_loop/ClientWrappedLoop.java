package socket_installer.SI.client.socket_actions.socket_loop;



import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.exception.client.general.ClientGeneralException;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.exception.default_exception.NoSolutionForException;

import java.io.IOException;

public class ClientWrappedLoop extends ProgramLoopWrapper {

    @Override
    public void activateWrappedLoop(SocketModel socketModel) throws NoSolutionForException {
        ClientGeneralException clientGeneralException = new ClientGeneralException();


        while(isProgramRunning() && socketModel.getSocketConfiguration().isSocketOnline()){
            try{
                System.out.println("ActivateWrappedLoop  "+Thread.currentThread().getId());
                socketModel.activateSocket();

            }catch (NoSolutionForException noSolutionException){
                System.out.println("no solution exception client wrapped loop");
                noSolutionException.handleException(socketModel);
            } catch (SocketExceptions socketExceptions){
                System.out.println("socket exception client wrapped loop");
                socketExceptions.handleException(socketModel);
            } catch (IOException ioException){
                System.out.println("handle general excpetion client wrapped loop");
                clientGeneralException.handleGeneralException(ioException,socketModel);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        threadClosingSetup((ClientSocket) socketModel);
    }

    @Override
    public void activateWrappedLoop(SocketModel socketModel,String classIdent,String methodIdent,String notification)throws IOException,SocketExceptions {
        ClientGeneralException clientGeneralException = new ClientGeneralException();


        if(socketModel.getSocketConfiguration().isSocketOnline()){
            try{
                System.out.println("ActivateWrappedLoop  "+Thread.currentThread().getId());
                socketModel.activateSocket(classIdent,methodIdent,notification);
            }catch (NoSolutionForException noSolutionException){
                noSolutionException.handleException(socketModel);
            } catch (SocketExceptions socketExceptions){
                System.out.println("socket exception client wrapped loop");
                socketExceptions.handleException(socketModel);
                throw socketExceptions;
            } catch (IOException ioException){
                System.out.println("handle general excpetion client wrapped loop");
                clientGeneralException.handleGeneralException(ioException,socketModel);
                throw ioException;
            }catch (Exception exception){
                exception.printStackTrace();
                throw exception;
            }
        }
        threadClosingSetup((ClientSocket) socketModel);
    }

    @Override
    public void activateWrappedLoopNoStreamOpen(SocketModel socketModel, String classIdent, String methodIdent, String notification) throws SocketExceptions, IOException {
        ClientGeneralException clientGeneralException = new ClientGeneralException();

        if(socketModel.getSocketConfiguration().isSocketOnline()){
            try{
                System.out.println("ActivateWrappedLoop  "+Thread.currentThread().getId());
                socketModel.activateSocketNoStreamOpen(classIdent,methodIdent,notification);
            }catch (NoSolutionForException noSolutionException){
                noSolutionException.handleException(socketModel);
            } catch (SocketExceptions socketExceptions){
                System.out.println("socket exception client wrapped loop");
                socketExceptions.handleException(socketModel);
                throw socketExceptions;
            } catch (IOException ioException){
                System.out.println("handle general excpetion client wrapped loop");
                clientGeneralException.handleGeneralException(ioException,socketModel);
                throw ioException;
            }catch (Exception exception){
                exception.printStackTrace();
                throw exception;
            }
        }
    }

    private void threadClosingSetup(ClientSocket clientSocket){
        ((ClientConfiguration)clientSocket.getSocketConfiguration()).setThreadId(null);
        ((ClientConfiguration)clientSocket.getSocketConfiguration()).resetTimeoutIncrease();
        clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorEnums.STREAM_CLOSED);
        System.out.println("Ovaj thread zavrsava" + Thread.currentThread().getId() + "--------------------------------------------------------------------------------------->\n\n\n");
    }

}
