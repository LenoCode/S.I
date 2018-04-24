package socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer;

public class StringBuffer {

    private final StringBuilder stringBuffer;

    public StringBuffer(){
        stringBuffer = new StringBuilder();
    }

    public void insertToBuffer(int bytesRead,byte[] bytes){
        stringBuffer.append(new String(bytes,0,bytesRead));
    }
    public String emptyBuffer(){
        String stringFromBuffer = stringBuffer.toString();
        stringBuffer.setLength(0);
        return stringFromBuffer;
    }

}
