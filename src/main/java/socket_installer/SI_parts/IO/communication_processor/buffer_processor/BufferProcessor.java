package socket_installer.SI_parts.IO.communication_processor.buffer_processor;

import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.util.Arrays;
import java.util.Iterator;

public class BufferProcessor {

    public boolean checkProtocolInBuffer(StringBuffer stringBuffer, String dataToFind){
        Iterator<String> iterator = Arrays.asList(stringBuffer.getString().split(EndMarkerProtocol.END_LINE.getProtocol())).iterator();
        String next = iterator.next();

        stringBuffer.emptyBuffer();
        if (next.equals(dataToFind)){
            return true;
        }else{
            return false;
        }
    }
    public Iterator<String> parseNotifications(StringBuffer stringBuffer){
        return Arrays.asList(stringBuffer.getString().split(EndMarkerProtocol.getEndRegex())).iterator();
    }
    public String removeSocketStreamClosedLine(StringBuffer stringBuffer){
        return stringBuffer.getString().replaceAll(TechnicalProtocol.getSocketCloseRegex(),"");
    }

    public String extractNotification(String string){
        int leftSideIndex = string.indexOf('>')+1;
        return string.substring(leftSideIndex);
    }
}
