package socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols;


import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolUndefinedModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;

public enum  ClientProtocol implements ProtocolUndefinedModel {

    SEND_MESSAGE("<SEND_MESSAGE>%s",ProcessorsEnums.DATA_COMPLETE),
    ;

    private String protocol;
    private ProcessorsEnums processorsEnums;

    ClientProtocol(String protocol,ProcessorsEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    public String completeProtocol() {
        return protocol+ EndUndefined.END_UNDEFINED_BYTES.getProtocol();
    }

    @Override
    public ProcessorsEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String getProtocol(){
        return protocol;
    }
}