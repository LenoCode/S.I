package socket_installer.SI.socket_creation.client.client_creator;

import socket_installer.SI.client.socket.Client;
import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI.client.socket_actions.socket_loop.ClientWrappedLoop;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.created_socket.client.ClientCreatedSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop.ProgramLoopWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;
import java.net.Socket;

public class ClientCreator {


    public ClientCreatedSocket createClientCreatedSocket(NotificationerActions notificationerActions, Socket socket,int timeout){
        return new ClientCreatedSocket() {

            @Override
            public void initSocket() throws IOException, SocketExceptions {
                ClientConfiguration clientConfiguration = new ClientConfiguration(socket);
                clientConfiguration.setTimeout(timeout);
                basicSocket = new Client(socket);
                basicSocket.setSocketConfiguration(clientConfiguration);
                basicSocket.setNotificationer(notificationerActions);
                basicSocket.setupSocket();
                socket.setSoTimeout(timeout);

                notificationerActions.callExternalInitializator();
                notificationerActions.setClientSocket((ClientSocket) basicSocket);
                for (DataTradeModel dataTradeModel : notificationerActions.getObjects()){
                    dataTradeModel.setClientSocket((ClientSocket) basicSocket);
                    dataTradeModel.injectExternalContext(notificationerActions.getExternalContext());
                }
            }

            @Override
            public void runSocket(String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions {
                System.out.println("ŠALJEM MESSAGE");
                ClientWrappedLoop clientWrappedLoop = new ClientWrappedLoop();
                clientWrappedLoop.activateWrappedLoop(basicSocket,classIdent,methodIdent,notification);
            }

            @Override
            public void closeProgram() {
                try {
                    CommunicationProcessor.MainProcessor().sendData((ClientSocket)basicSocket,TechnicalProtocol.SOCKET_CLOSED.completeProtocol().getBytes());
                    basicSocket.getSocketConfiguration().setSocketOnlineStatus(false);
                    ProgramLoopWrapper.setProgrammRunning(false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SocketExceptions socketExceptions) {
                    socketExceptions.printStackTrace();
                }
            }
        };
    }
}
