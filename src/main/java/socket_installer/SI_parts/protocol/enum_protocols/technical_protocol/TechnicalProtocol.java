package socket_installer.SI_parts.protocol.enum_protocols.technical_protocol;

import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

public enum TechnicalProtocol{
    SOCKET_STREAM_OPEN("</SOCKET_STREAM_OPEN>Stream open>",ProcessorsEnums.STREAM_OPEN),
    SOCKET_STREAM_CLOSED("<SOCKET_STREAM_CLOSED>Stream closed>",ProcessorsEnums.STREAM_CLOSED),
    SOCKET_CLOSED("</SOCKET_CLOSED>Socket closed>",ProcessorsEnums.SOCKET_CLOSED),
    ;


    private String protocol;
    private ProcessorsEnums processorsEnums;

    TechnicalProtocol(String protocol, ProcessorsEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    public String completeProtocol() {
        return protocol + EndMarkerProtocol.END_TRANSFER.getProtocol();
    }

    public ProcessorsEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String identProtocol(){
        return protocol;
    }
}
