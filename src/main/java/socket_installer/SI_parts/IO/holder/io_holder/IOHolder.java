package socket_installer.SI_parts.IO.holder.io_holder;

import socket_installer.SI_behavior.abstractClasses.sockets.io.streams.InputStreamWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.io.streams.OutputStreamWrapper;
import socket_installer.SI_behavior.abstractClasses.sockets.io.streams.StreamWrapper;

import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;


public class IOHolder {


    private InputStreamWrapper inputStream;
    private OutputStreamWrapper outputStream;
    private StringBuffer stringBuffer;
    private byte[] bytes;

    public IOHolder(){
        bytes = new byte[10];
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }


    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }


    public byte[] getBytes() {
        return bytes;
    }

    public InputStreamWrapper getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStreamWrapper inputStreamWrapperModel) {
        this.inputStream = inputStreamWrapperModel;
    }

    public OutputStreamWrapper getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStreamWrapper outputStreamWrapperModel) {
        this.outputStream = outputStreamWrapperModel;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
