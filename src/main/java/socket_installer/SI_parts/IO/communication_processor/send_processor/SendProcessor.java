package socket_installer.SI_parts.IO.communication_processor.send_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import java.io.IOException;

public class SendProcessor {

    public void send(OutputStreamWrapperModel outputStreamWrapperModel,byte[] bytes)throws IOException,SocketExceptions{
        outputStreamWrapperModel.send(bytes);
    }

}
