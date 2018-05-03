package socket_installer.SI_parts.socket_actions.recv_response.string_parser;
import socket_installer.SI_parts.socket_actions.recv_response.string_parser.enum_parser.RegexParser;
import socket_installer.SI_parts.protocol.enum_protocol.GeneralProtocol;

import java.util.Arrays;
import java.util.Iterator;

public class StringParser {

    private static StringParser stringParser;

    private StringParser(){

    }

    public static  StringParser getStringParser(){
        if (stringParser == null){
            stringParser = new StringParser();
        }
        return stringParser;
    }

    public Iterator<String> parseForStrings(String string){
        if (string.endsWith(GeneralProtocol.END_BYTES.getProtocol())){
            return Arrays.asList(string.split(RegexParser.END_PROTOCOL.getRegex())).iterator();
        }
        return null;
    }
}
