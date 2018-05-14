package socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols;


import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolDefinedModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;

public enum TehnicalProtocol implements ProtocolDefinedModel {
    SOCKET_CLOSED("</SOCKET_CLOSED>Socket closed>",ProcessorsEnums.SOCKET_CLOSED),
    ;

    private String protocol;
    private ProcessorsEnums processorsEnums;

    TehnicalProtocol(String protocol,ProcessorsEnums processorsEnums){
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