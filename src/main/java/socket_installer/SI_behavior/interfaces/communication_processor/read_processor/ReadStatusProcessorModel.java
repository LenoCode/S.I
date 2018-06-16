package socket_installer.SI_behavior.interfaces.communication_processor.read_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;

import java.io.IOException;

public interface ReadStatusProcessorModel {
    boolean checkStreamStatus(ClientSocket clientSocket)throws SocketExceptions, IOException;
    boolean checkIfStreamOpen();
    ProcessorsEnums checkReadStatus();
    void setCheckReadStatus(ProcessorsEnums processorsEnums);
    void setStreamOpenStatus(ProcessorsEnums processorsEnums);
}
