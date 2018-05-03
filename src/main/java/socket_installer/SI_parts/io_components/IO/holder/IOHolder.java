package socket_installer.SI_parts.io_components.IO.holder;

import socket_installer.SI_parts.io_components.IO.wrapper.InputStreamWrapper;
import socket_installer.SI_parts.io_components.IO.wrapper.OutputStreamWrapper;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;


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

    public void setInputStream(BufferedInputStream bufferedInputStream) {
        this.inputStream = new InputStreamWrapper(bufferedInputStream);
    }

    public OutputStreamWrapper getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(BufferedOutputStream bufferedOutputStream) {
        this.outputStream = new OutputStreamWrapper(bufferedOutputStream);
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
