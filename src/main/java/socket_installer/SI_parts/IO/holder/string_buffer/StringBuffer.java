package socket_installer.SI_parts.IO.holder.string_buffer;


import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

public class StringBuffer {

    private final StringBuilder stringBuffer;

    public StringBuffer(){
        stringBuffer = new StringBuilder();
    }

    public void insertToBuffer(int bytesRead,byte[] bytes){
        stringBuffer.append(new String(bytes,0,bytesRead));
    }
    public void insertToBuffer(String string){
        stringBuffer.append(string);
    }

    public String getString(){
        return stringBuffer.toString();
    }

    public void emptyBuffer(){
        stringBuffer.setLength(0);
    }

    public void removeFirstNotification(String notification){
        notification = notification.concat(EndMarkerProtocol.END_LINE.getProtocol());
        String newData = stringBuffer.toString();
        newData = newData.replace(notification,"");
        stringBuffer.setLength(0);
        stringBuffer.append(newData);
    }
}
