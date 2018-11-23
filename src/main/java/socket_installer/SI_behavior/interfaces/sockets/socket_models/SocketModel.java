package socket_installer.SI_behavior.interfaces.sockets.socket_models;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.configuration_models.SocketConfiguration;

import java.io.IOException;

/***
 *
 * A - socket configuration model
 *
 */

public interface SocketModel{
    default void activateSocket() throws IOException,SocketExceptions{}
    default void activateSocket(String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions{}
    default void activateSocketNoStreamOpen(String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions{}
    void deactivateSocket() throws IOException,SocketExceptions;
    void setupSocket() throws IOException, SocketExceptions;
    void setSocketConfiguration(SocketConfiguration socketConfiguration);
    SocketConfiguration getSocketConfiguration();
}
