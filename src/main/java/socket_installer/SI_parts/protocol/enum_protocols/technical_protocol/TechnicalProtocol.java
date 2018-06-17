package socket_installer.SI_parts.protocol.enum_protocols.technical_protocol;

import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

public enum TechnicalProtocol{
    SOCKET_STREAM_OPEN("</SOCKET_STREAM_OPEN>Stream open>",ProcessorEnums.STREAM_OPEN),
    SOCKET_STREAM_CLOSED("<SOCKET_STREAM_CLOSED>Stream closed>",ProcessorEnums.STREAM_CLOSED),
    SOCKET_STREAM_CLOSING("<SOCKET_STREAM_CLOSING>Stream closing>",ProcessorEnums.STREAM_CLOSING),
    SOCKET_CLOSED("</SOCKET_CLOSED>Socket closed>",ProcessorEnums.SOCKET_CLOSED),
    ;


    private String protocol;
    private ProcessorEnums processorsEnums;

    TechnicalProtocol(String protocol, ProcessorEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    public String completeProtocol() {
        return protocol + EndMarkerProtocol.END_TRANSFER.getProtocol();
    }

    public ProcessorEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String identProtocol(){
        return protocol;
    }

    public static String getSocketCloseRegex(){
        return "(<SOCKET_STREAM_CLOSING>Stream closing></END TRANSFER>Finished>|<SOCKET_STREAM_CLOSED>Stream closed></END TRANSFER>Finished>)";
    }
}
