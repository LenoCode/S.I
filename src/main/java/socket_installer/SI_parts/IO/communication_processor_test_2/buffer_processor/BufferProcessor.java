package socket_installer.SI_parts.IO.communication_processor_test_2.buffer_processor;

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
    public Iterator<String> parseDataRecieved(StringBuffer stringBuffer){
        return Arrays.asList(stringBuffer.getString().split(EndMarkerProtocol.END_LINE.getProtocol())).iterator();
    }
    public void removeSocketStreamClosedLine(StringBuffer stringBuffer){
        String replacedData =stringBuffer.getString().replace(TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol(),"");
        stringBuffer.emptyBuffer();
        stringBuffer.insertToBuffer(replacedData);

    }
    public String extractNotification(String string){
        int leftSideIndex = string.indexOf('>')+1;
        return string.substring(leftSideIndex);
    }
}
