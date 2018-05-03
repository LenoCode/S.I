package socket_installer.SI_parts.socket_actions.recv_response.string_buffer;

public class StringBuffer {

    private final StringBuilder stringBuffer;

    public StringBuffer(){
        stringBuffer = new StringBuilder();
    }

    public void insertToBuffer(int bytesRead,byte[] bytes){
        stringBuffer.append(new String(bytes,0,bytesRead));
    }

    public String getString(){
        return stringBuffer.toString();
    }

    public void emptyBuffer(){
        stringBuffer.setLength(0);
    }

}
