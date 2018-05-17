package socket_installer.SI_parts.actionHolder.actions.notification_parser;

import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.socket_models.SocketModel;
import socket_installer.SI_parts.actionHolder.actions.string_parser.enum_parser.RegexParser;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.EndUndefined;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class NotificationParser {

    public Iterator<String> getUnparsedIteratorNotification(String string)throws IOException,SocketExceptions {
        if (string.endsWith(EndUndefined.END_UNDEFINED_BYTES.getProtocol())) {
            return Arrays.asList(string.split(RegexParser.END_PROTOCOL.getRegex())).iterator();
        }
        throw new SocketExceptions() {
            @Override
            public void handleException(SocketModel socketModel) {
                //OVDJE CE TREBATI NAPRAVITI NEKI EXCEPTION ZA HANDLANJE, IAKO NE BI TREBALO DOCI DO TOGA NO ZA SVAKI SLUCAJ
            }
        };
    }

    public String extractNotification(String string){
        int leftSideIndex = string.indexOf('>')+1;
        return string.substring(leftSideIndex);
    }
}
