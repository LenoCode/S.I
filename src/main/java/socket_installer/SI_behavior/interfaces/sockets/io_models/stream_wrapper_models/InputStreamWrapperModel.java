package socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;

import java.io.IOException;

public interface InputStreamWrapperModel {
    void read(byte[] bytes,StringBuffer buffer) throws IOException, SocketExceptions;
    int read(byte[] bytes) throws IOException, SocketExceptions;
    boolean dataAvailable() throws IOException,SocketExceptions;
}
