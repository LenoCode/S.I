package socket_installer.SI_parts.io_components.IO.processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.io_components.IO.wrapper.client.ClientInputStreamWrapper;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;
import socket_installer.SI_parts.protocol.enum_protocol.BytesStatusProtocol;

import java.io.IOException;

public class IOProcessor {
    private static IOProcessor ioProcessor;

    private IOProcessor() {
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

    public void initializeBytesSending(byte[] bytesToSend, IOHolder ioHolder) throws IOException, SocketExceptions {
        OutputStreamWrapperModel outputStreamWrapper = ioHolder.getOutputStream();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();

        sendBytes(bytesToSend, outputStreamWrapper);

        stringBuffer.emptyBuffer();
    }

    private void sendBytes(byte[] bytesToSend, OutputStreamWrapperModel outputStreamWrapper) throws IOException, SocketExceptions {
        outputStreamWrapper.send(bytesToSend);
    }


    private boolean checkIfBytesSuccessfulySent(byte[] bytesToRecv, StringBuffer buffer, ClientInputStreamWrapper inputStreamWrapper) throws IOException, SocketExceptions {
        inputStreamWrapper.read(bytesToRecv, buffer);
        if (!buffer.getString().equals(BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol())) {
            throw new IOException("KING KONG");
        }
        return true;
    }

}
