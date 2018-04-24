package socket_installer.SI_parts.io_components.IO;


import socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer.StringBuffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class IOHolder {


    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;
    private StringBuffer stringBuffer;
    private byte[] bytes;

    public IOHolder(){
        bytes = new byte[1024];
    }

    public void setInStream(InputStream bufferedInputStream) {
        this.inputStream = new BufferedInputStream(bufferedInputStream);
    }


    public void setOutStream(OutputStream bufferedOutputStream) {
        this.outputStream = new BufferedOutputStream(bufferedOutputStream);
    }


    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }


    public BufferedInputStream getInStream() {
        return inputStream;
    }


    public BufferedOutputStream getOutStream() {
        return outputStream;
    }


    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }


    public byte[] getBytes() {
        return bytes;
    }
}
