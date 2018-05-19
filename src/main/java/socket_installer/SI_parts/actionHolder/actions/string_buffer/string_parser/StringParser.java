package socket_installer.SI_parts.actionHolder.actions.string_buffer.string_parser;


import socket_installer.SI_parts.protocol.enum_protocol.GeneralProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.EndDefined;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.EndUndefined;


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

    public GeneralProtocol parseForStrings(String string){
        if (string.contains(EndDefined.END_DEFINED_BYTES.getProtocol())){
            return GeneralProtocol.DEFINED;
        }
        else if (string.endsWith(EndUndefined.END_UNDEFINED_BYTES.getProtocol())){
            return GeneralProtocol.UNDEFINED;
        }
        else if (string.length() > 0){
            return GeneralProtocol.UNFINISHED_DATA;
        }
        return GeneralProtocol.NO_DATA;
    }
}
