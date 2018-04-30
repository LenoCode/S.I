package socket_installer.SI_parts.socket_actions.response_actions;

import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_buffer.StringBuffer;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_parser.StringParser;

import java.util.Iterator;

public class BufferChecker {


    public void checkStringBuffer(StringBuffer stringBuffer, Notificationer notificationer){
        Iterator<String> stringIterator = StringParser.getStringParser().parseForStrings(stringBuffer.getString());
        if (stringIterator != null){
            while(stringIterator.hasNext()){
                notificationer.notificationRecv(stringIterator.next());
            }
        }
    }
}
