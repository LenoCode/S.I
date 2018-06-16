package socket_installer.SI_parts.protocol.enum_protocols.communication_protocol;

import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.EndDefined;

public enum CommunicationProtocol {
    BYTES_SEND_SUCCESS("<BYTES_STATUS>Bytes request_annotation success",ProcessorsEnums.BYTES_SENT_SUCCESS),
    BYTES_SEND_FAILED("<BYTES_STATUS>Bytes request_annotation failed",ProcessorsEnums.BYTES_SENT_FALIED),
    ;

    private String protocol;
    private ProcessorsEnums processorsEnums;

    CommunicationProtocol(String protocol,ProcessorsEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    public String completeProtocol() {
        return protocol+ EndDefined.END_DEFINED_BYTES.getProtocol();
    }

    public ProcessorsEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String IdentProtocol(){
        return protocol;
    }
}
