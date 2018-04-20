package socket_installer.SI_parts.io_components;

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
