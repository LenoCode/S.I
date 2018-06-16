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

public class ClientMainProcessor extends MainProcessor {


    public void openStreamSocket(ClientSocket clientSocket) throws IOException, SocketExceptions {
        String dataToSend = TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();

        notifyServerAboutOpendStream(clientSocket,readStatusProcessorModel,dataToSend);

        if (bufferProcessor.checkProtocolInBuffer(stringBuffer,TechnicalProtocol.SOCKET_STREAM_OPEN.completeProtocol())){
            clientSocket.getActions().getReadStatusProcessorModel().setStreamOpenStatus(ProcessorsEnums.STREAM_OPEN);
        }else{
            throw  new ClientClosedException();
        }
    }
    public void readingDataFromStream(ClientSocket clientSocket) throws IOException, SocketExceptions{
        ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
        NotificationerActions notificationerActions = clientSocket.getNotificationer();
        StringBuffer stringBuffer = clientSocket.getIOHolder().getStringBuffer();

        setInputStreamToBlock(clientSocket);
        do{
            readProcessor.readDataFromOpenStream(clientSocket,readStatusProcessorModel);
            checkStatusFromReadStatusProcessor(readStatusProcessorModel,notificationerActions,stringBuffer);
        }while(readStatusProcessorModel.checkIfStreamOpen());

        checkForDataBeforeClosing(notificationerActions,stringBuffer);
        setInputStreamToUnblock(clientSocket);

        while(true){

        }
    }


    private void checkStatusFromReadStatusProcessor(ReadStatusProcessorModel readStatusProcessorModel, NotificationerActions notificationerActions, StringBuffer stringBuffer) throws IOException, SocketExceptions {

        if (readStatusProcessorModel.checkReadStatus() == ProcessorsEnums.DATA_LINE_COMPLETE){
            notifyClass(notificationerActions,stringBuffer);
        }else if (readStatusProcessorModel.checkReadStatus() == ProcessorsEnums.DATA_COMPLETE){
            readStatusProcessorModel.setStreamOpenStatus(ProcessorsEnums.STREAM_CLOSED);
        }else if (readStatusProcessorModel.checkReadStatus() == ProcessorsEnums.STREAM_CONNECTION_LOST){
            notificationerActions.exceptionHandler(readStatusProcessorModel);
        }
    }


    private void notifyServerAboutOpendStream(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel, String dataToSend) throws IOException, SocketExceptions {
        readStatusProcessorModel.setCheckReadStatus(ProcessorsEnums.INITILIAZED);
        do {
            ProcessorsEnums readStatus = readStatusProcessorModel.checkReadStatus();

            if (readStatus == ProcessorsEnums.INITILIAZED || readStatus == ProcessorsEnums.FIRST_TRY){
                sendData(clientSocket,dataToSend.getBytes());
            }
            readProcessor.readStreamStatus(clientSocket,readStatusProcessorModel);
        }while(readStatusProcessorModel.checkStreamStatus(clientSocket));


    }

}
