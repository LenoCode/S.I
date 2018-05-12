package socket_installer.SI_parts.io_components.IO.processor;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_parts.data_carriers.response_carrier.ResponseCarrier;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.socket_actions.ActionHolder;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.ProtocolCheck;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.IOException;
import java.util.Iterator;

public class IOProcessor {
    private static IOProcessor ioProcessor;
    private final ProtocolCheck protocolCheck;

    private IOProcessor() {
        protocolCheck = new ProtocolCheck();
    }

    public static IOProcessor getIoProcessor() {
        if (ioProcessor == null) {
            ioProcessor = new IOProcessor();
        }
        return ioProcessor;
    }


    public void initializeBytesReading(IOHolder ioHolder) throws  IOException,SocketExceptions{
        byte[] bytes = ioHolder.getBytes();
        StringBuffer buffer = ioHolder.getStringBuffer();
        InputStreamWrapperModel inputStream = ioHolder.getInputStream();
        inputStream.read(bytes,buffer);
    }

    //OVO SAMO CLIENT KORISTI
    public void initializeBytesSending(byte[] bytesToSend, ClientSocket clientSocket) throws IOException, SocketExceptions {
        IOHolder ioHolder = clientSocket.getIOHolder();
        OutputStreamWrapperModel outputStreamWrapper = ioHolder.getOutputStream();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();

        outputStreamWrapper.send(bytesToSend,clientSocket);
    }


    public void checkBytesReadClient(ActionHolder actionHolder, ClientSocket clientSocket)throws IOException, SocketExceptions{
        ResponseCarrier responseCarrier = clientSocket.getIOHolder().getResponseCarrier();
        actionHolder.getBufferChecker().checkStringBuffer(clientSocket);

        if (responseCarrier.getStringResponses() != null){
            actionHolder.getResponseHandler().parseIteratorForResponse(responseCarrier,clientSocket.getNotificationer(),protocolCheck);
        }



    }
    public void checkBytesReadClientConnected(ActionHolder actionHolder, ClientSocket clientSocket)throws IOException, SocketExceptions{
        ResponseCarrier responseCarrier = clientSocket.getIOHolder().getResponseCarrier();
        actionHolder.getBufferChecker().checkStringBuffer(clientSocket);

        if (responseCarrier.getStringResponses() != null){
            actionHolder.getBytesResponder().sendBytesRecv(clientSocket.getIOHolder());
            actionHolder.getResponseHandler().parseIteratorForResponse(responseCarrier,clientSocket.getNotificationer(),protocolCheck);
        }
    }

}
