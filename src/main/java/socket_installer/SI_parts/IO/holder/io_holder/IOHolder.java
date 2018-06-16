package socket_installer.SI_parts.IO.holder.io_holder;

import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;

import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;



public class IOHolder {


    private InputStreamWrapperModel inputStream;
    private OutputStreamWrapperModel outputStream;
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

    public InputStreamWrapperModel getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStreamWrapperModel inputStreamWrapperModel) {
        this.inputStream = inputStreamWrapperModel;
    }

    public OutputStreamWrapperModel getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStreamWrapperModel outputStreamWrapperModel) {
        this.outputStream = outputStreamWrapperModel;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
