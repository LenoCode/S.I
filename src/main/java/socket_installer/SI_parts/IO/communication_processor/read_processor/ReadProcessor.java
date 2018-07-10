package socket_installer.SI_parts.IO.communication_processor.read_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientClosedException;
import socket_installer.SI_parts.exception.client.general.ClientTimeoutException;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

import java.io.IOException;

public class ReadProcessor {

    public void readStreamStatus(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions{
        try{
            System.out.println("read stream status "+Thread.currentThread().getId());
            read(clientSocket,readStatusProcessorModel);
            System.out.println("read stream status ok "+Thread.currentThread().getId());
        }catch (ClientTimeoutException socketTimeoutException){
            System.out.println("read stream status client timeout exception [ "+readStatusProcessorModel.checkReadStatus() +" ]  "+Thread.currentThread().getId());
            readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.increaseProccesorCount(readStatusProcessorModel.checkReadStatus()));
        }catch (ClientClosedException|ConnectedClientClosedException closedException){
            System.out.println("read stream status client closed exception [ "+readStatusProcessorModel.checkReadStatus() +" ]  "+Thread.currentThread().getId());
            readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.increaseProccesorCount(readStatusProcessorModel.checkReadStatus()));
        }
    }

    public void readDataFromOpenStream(ClientSocket clientSocket,ReadStatusProcessorModel readStatusProcessorModel) throws IOException, SocketExceptions {
        try{
            System.out.println("reading client messages" + Thread.currentThread().getId());
            read(clientSocket,readStatusProcessorModel);
        }catch (ConnectedClientClosedException|ClientClosedException|ClientTimeoutException closedException){
            System.out.println("setting stream status to stream connection lost");
            readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.STREAM_CONNECTION_LOST);
        }catch (IOException ioException){
            throw ioException;
        }
    }
    public int readBytesFromStream(ClientSocket clientSocket,byte[] bytes) throws IOException, SocketExceptions{
        InputStreamWrapperModel inputStreamWrapperModel = clientSocket.getIOHolder().getInputStream();
        return inputStreamWrapperModel.read(bytes);
    }


    private void read(ClientSocket clientSocket, ReadStatusProcessorModel readStatusProcessorModel)throws IOException, SocketExceptions{
        IOHolder ioHolder = clientSocket.getIOHolder();
        InputStreamWrapperModel inputStreamWrapperModel = ioHolder.getInputStream();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();
        byte[] bytes = ioHolder.getBytes();

        inputStreamWrapperModel.read(bytes,stringBuffer);
        readStatusProcessorModel.setCheckReadStatus(checkStringBuffer(stringBuffer));
        System.out.println("------------> Checking string buffer end "+Thread.currentThread().getId());

    }

    private ProcessorEnums checkStringBuffer(StringBuffer stringBuffer)throws IOException, SocketExceptions{
        String stringInBuffer = stringBuffer.getString();
        System.out.println("Checking string buffer client read message ...... >" + Thread.currentThread().getId());
        if (stringInBuffer.endsWith(EndMarkerProtocol.END_TRANSFER.getProtocol())){
            System.out.println("data complete   "+stringInBuffer +  "     "  + Thread.currentThread().getId() );
            return ProcessorEnums.DATA_COMPLETE;

        }else if (stringInBuffer.endsWith(EndMarkerProtocol.END_LINE.getProtocol())){
            System.out.println("data line complete  "+"   "+stringInBuffer+"  "+Thread.currentThread().getId());
            return ProcessorEnums.DATA_LINE_COMPLETE;
        }
        else if (stringInBuffer.length() > 0){
            System.out.println("data incomplete    "+stringInBuffer);
            return ProcessorEnums.DATA_INCOMPLETE;
        }else{
            throw new ClientTimeoutException();
        }
    }
}
