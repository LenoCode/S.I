package socket_installer.SI_parts.io_components.IO.processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.io_components.IO.holder.IOHolder;
import socket_installer.SI_parts.io_components.IO.wrapper.InputStreamWrapper;
import socket_installer.SI_parts.io_components.IO.wrapper.OutputStreamWrapper;
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
        InputStreamWrapper inputStream = ioHolder.getInputStream();
        inputStream.read(bytes,buffer);
    }

    public void initializeBytesSending(byte[] bytesToSend, IOHolder ioHolder) throws IOException, SocketExceptions {
        OutputStreamWrapper outputStreamWrapper = ioHolder.getOutputStream();
        InputStreamWrapper inputStreamWrapper = ioHolder.getInputStream();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();
        byte[] bytesToRecv = ioHolder.getBytes();

        sendBytes(bytesToSend, outputStreamWrapper);
        checkIfBytesSuccessfulySent(bytesToRecv, stringBuffer, inputStreamWrapper);

        stringBuffer.emptyBuffer();
    }

    private void sendBytes(byte[] bytesToSend, OutputStreamWrapper outputStreamWrapper) throws IOException, SocketExceptions {
        outputStreamWrapper.send(bytesToSend);
    }


    private boolean checkIfBytesSuccessfulySent(byte[] bytesToRecv, StringBuffer buffer, InputStreamWrapper inputStreamWrapper) throws IOException, SocketExceptions {
        inputStreamWrapper.read(bytesToRecv, buffer);
        if (!buffer.getString().equals(BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol())) {
            throw new IOException("KING KONG");
        }
        return true;
    }

}
