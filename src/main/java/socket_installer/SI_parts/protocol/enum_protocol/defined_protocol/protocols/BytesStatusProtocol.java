package socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols;


import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolDefinedModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;

public enum BytesStatusProtocol implements ProtocolDefinedModel {
    BYTES_SEND_SUCCESS("<BYTES_STATUS>Bytes request_annotation success",ProcessorsEnums.BYTES_SENT_SUCCESS),
    BYTES_SEND_FAILED("<BYTES_STATUS>Bytes request_annotation failed",ProcessorsEnums.BYTES_SENT_FALIED),
    ;

    private String protocol;
    private ProcessorsEnums processorsEnums;

    BytesStatusProtocol(String protocol,ProcessorsEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    @Override
    public String completeProtocol() {
        return protocol+ EndDefined.END_DEFINED_BYTES.getProtocol();
    }

    @Override
    public ProcessorsEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String IdentProtocol(){
        return protocol;
    }

}
