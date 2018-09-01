package socket_installer.SI_parts.IO.communication_processor.main_processors;

import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;
import java.io.IOException;

public class ClientMainProcessor extends MainProcessor {


    public void openStreamSocket(ClientSocket clientSocket) throws IOException, SocketExceptions {
        String dataToSend = TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        System.out.println("OPENING STREAM ----------");

        if (checkIfStreamNeedsToOpen(clientSocket,readStatusProcessorModel)){
            notifyServerAboutOpendStream(clientSocket,readStatusProcessorModel,dataToSend);

            if (bufferProcessor.checkProtocolInBuffer(stringBuffer,TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol())){
                clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorEnums.STREAM_OPEN);
            }else{
                throw new ClientClosedException();
            }
        }
    }

    private boolean checkIfStreamNeedsToOpen(ClientSocket clientSocket,ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        return !clientSocket.getIOHolder().getInputStream().dataAvailable() || !readStatusProcessorModel.checkIfStreamOpen();
    }

    private void notifyServerAboutOpendStream(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel, String dataToSend) throws IOException, SocketExceptions {
        readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.INITILIAZED);
        do {
            ProcessorEnums readStatus = readStatusProcessorModel.checkReadStatus();

            if (readStatus != ProcessorEnums.DATA_COMPLETE && readStatus != ProcessorEnums.DATA_INCOMPLETE && readStatus != ProcessorEnums.FIFTH_TRY){
                sendData(clientSocket,dataToSend.getBytes());
            }
            readProcessor.readStreamStatus(clientSocket,readStatusProcessorModel);

        }while(readStatusProcessorModel.checkStreamStatus(clientSocket));
    }




}
