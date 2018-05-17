package socket_installer.SI_behavior.abstractClasses.sockets.socket.client;

import socket_installer.SI.client.socket.ClientConfiguration;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.BasicSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.IOHolderSetupMethodModel;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.actionHolder.ActionHolder;


import java.io.IOException;
import java.net.Socket;

public abstract class ClientSocket extends BasicSocket implements IOHolderSetupMethodModel {

    protected IOHolder ioHolder;
    protected static ActionHolder actions = new ActionHolder();

    public ClientSocket(Socket clientSocket){
        this.socket = clientSocket;
    }

    @Override
    public void setupSocket() throws IOException,SocketExceptions{
        ClientConfiguration clientConfiguration = (ClientConfiguration) getSocketConfiguration();
        setupIOHolder();
        clientConfiguration.setStreamPaused(false);
        clientConfiguration.setSocketOnlineStatus(true);
    }


    public IOHolder getIOHolder() {
        return ioHolder;
    }

    public ActionHolder getActions(){
        return actions;
    }

}
