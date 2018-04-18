package S.I_behavior.non_abstract_classes.io_components;


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
        inputStream = new BufferedInputStream(bufferedInputStream);
    }


    public void setOutStream(OutputStream bufferedOutputStream) {
        outputStream = new BufferedOutputStream(bufferedOutputStream);
    }


    public void setStringBuffer(StringBuffer stringBuffer) {
        stringBuffer = stringBuffer;
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
