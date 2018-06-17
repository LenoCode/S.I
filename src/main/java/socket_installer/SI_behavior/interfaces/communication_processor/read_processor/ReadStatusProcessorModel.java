package socket_installer.SI_behavior.interfaces.communication_processor.read_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;

import java.io.IOException;

public interface ReadStatusProcessorModel {
    boolean checkStreamStatus(ClientSocket clientSocket)throws SocketExceptions, IOException;
    boolean checkIfStreamOpen();
    ProcessorEnums getStreamClosingStatus();
    ProcessorEnums checkReadStatus();
    void setCheckReadStatus(ProcessorEnums processorsEnums);
    void setStreamOpenStatus(ProcessorEnums processorsEnums);
}
