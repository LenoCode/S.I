package socket_installer.SI_parts.io_components.parts_for_bytes.string_parser;
import socket_installer.SI_parts.io_components.parts_for_bytes.string_parser.enum_parser.RegexParser;
import socket_installer.SI_parts.protocol.enum_protocol.Protocol;

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
        if (string.endsWith(Protocol.END_PROTOCOL.IdentProtocol())){
            return Arrays.asList(string.split(RegexParser.END_PROTOCOL.getRegex())).iterator();
        }
        return null;
    }
}
