package socket_installer.SI_parts.IO.communication_processor.main_processors;

import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientClosedException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;

public class ConnectedClientMainProcessor extends MainProcessor {


    public void checkIfStreamReadyToOpen(ClientSocket clientSocket) throws IOException, SocketExceptions {
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        System.out.println("Checking if stream ready to open  "+Thread.currentThread().getId());
        readToCheckIfStreamOpend(clientSocket,readStatusProcessorModel);

        if (bufferProcessor.checkProtocolInBuffer(stringBuffer,TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol())){
            clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorEnums.STREAM_OPEN);
            sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol().getBytes());
        }else{
            throw new ConnectedClientClosedException();
        }
    }


    private void readToCheckIfStreamOpend(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel) throws IOException,SocketExceptions{
        readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.INITILIAZED);

        do {
            readProcessor.readStreamStatus(clientSocket,readStatusProcessorModel);
        }while(readStatusProcessorModel.checkStreamStatus(clientSocket));
    }

}
