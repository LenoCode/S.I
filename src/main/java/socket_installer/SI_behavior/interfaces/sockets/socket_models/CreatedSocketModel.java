package socket_installer.SI_behavior.interfaces.sockets.socket_models;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import java.io.IOException;

public interface CreatedSocketModel {
    void initSocket() throws IOException, SocketExceptions;
    default void runSocket(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions{}
    default void runSocketNoStreamOpen(String classIdent,String methodIdent,String data) throws IOException, SocketExceptions{}
    default void runSocket() throws IOException, SocketExceptions{}
    void closeProgram();
}
