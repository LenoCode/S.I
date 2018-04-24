package socket_installer.SI_behavior.abstractClasses.sockets;

import socket_installer.SI_behavior.interfaces.sockets.socket_models.CreatedSocketModel;


public abstract class CreatedSocket implements CreatedSocketModel {
    private BasicSocket basicSocket;

    protected void instatiateSocket(BasicSocket basicSocket){
        this.basicSocket = basicSocket;
    }
    pro
}
