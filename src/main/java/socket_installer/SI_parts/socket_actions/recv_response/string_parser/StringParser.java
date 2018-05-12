package socket_installer.SI_parts.socket_actions.recv_response.string_parser;
import socket_installer.SI_parts.data_carriers.carrier_enums.ResponseCarrierEnum;
import socket_installer.SI_parts.data_carriers.response_carrier.ResponseCarrier;

import socket_installer.SI_parts.protocol.enum_protocol.GeneralProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.string_parser.enum_parser.RegexParser;

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

    public void parseForStrings(String string, ResponseCarrier responseCarrier){
        if (string.contains(GeneralProtocol.END_DEFINED_BYTES.getProtocol())){
            Iterator<String> iterator = Arrays.asList(new String[]{string}).iterator();
            responseCarrier.setStringResponses(iterator);
            responseCarrier.setResponseCarrierEnum(ResponseCarrierEnum.DEFINED_RESPONSE);
        }
        else if (string.endsWith(GeneralProtocol.END_UNDEFINED_BYTES.getProtocol())){
            Iterator<String> iterator = Arrays.asList(string.split(RegexParser.END_PROTOCOL.getRegex())).iterator();
            responseCarrier.setStringResponses(iterator);
            responseCarrier.setResponseCarrierEnum(ResponseCarrierEnum.UNDEFINED_RESPONSE);
        }
    }
}
