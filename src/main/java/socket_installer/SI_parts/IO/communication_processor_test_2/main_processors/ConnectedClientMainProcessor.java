package socket_installer.SI_parts.IO.communication_processor_test_2.main_processors;

import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_behavior.abstractClasses.notification.notificationer_actions.NotificationerActions;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.IOException;

public class ConnectedClientMainProcessor extends MainProcessor {


    public void checkIfStreamReadyToOpen(ClientSocket clientSocket) throws IOException, SocketExceptions {
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();

        readToCheckIfStreamOpend(clientSocket,readStatusProcessorModel);

        if (bufferProcessor.checkProtocolInBuffer(stringBuffer,TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol())){
            clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorsEnums.STREAM_OPEN);
            sendProcessor.send(clientSocket.getIOHolder().getOutputStream(),TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol().getBytes());
        }else{
            throw new ClientClosedException();
        }
    }

    public void readingDataFromStream(ClientSocket clientSocket) throws IOException, SocketExceptions{
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        NotificationerActions notificationerActions = clientSocket.getNotificationer();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();

        do{
            readProcessor.readDataFromOpenStream(clientSocket,readStatusProcessorModel);

            if (readStatusProcessorModel.checkReadStatus() == ProcessorsEnums.DATA_LINE_COMPLETE){
                System.out.println("DATA LINE COMPLETE");
                notifyClass(notificationerActions,stringBuffer);
            }else if (readStatusProcessorModel.checkReadStatus() == ProcessorsEnums.DATA_COMPLETE){
                System.out.println("Data complete");
            }
        }while(readStatusProcessorModel.checkIfStreamOpen());
        System.out.println("STREAM CLOSED");
    }

    private void readToCheckIfStreamOpend(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel) throws IOException,SocketExceptions{
        readStatusProcessorModel.setCheckReadStatus(ProcessorsEnums.INITILIAZED);
        do {
            readProcessor.readStreamStatus(clientSocket,readStatusProcessorModel);
        }while(readStatusProcessorModel.checkStreamStatus(clientSocket));
    }
}
