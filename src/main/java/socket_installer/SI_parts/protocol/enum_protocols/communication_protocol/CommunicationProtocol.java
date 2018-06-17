package socket_installer.SI_parts.protocol.enum_protocols.communication_protocol;


import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;

public enum CommunicationProtocol {
    BYTES_SEND_SUCCESS("<BYTES_STATUS>Bytes request_annotation success",ProcessorEnums.BYTES_SENT_SUCCESS),
    BYTES_SEND_FAILED("<BYTES_STATUS>Bytes request_annotation failed",ProcessorEnums.BYTES_SENT_FALIED),
    ;

    private String protocol;
    private ProcessorEnums processorsEnums;

    CommunicationProtocol(String protocol,ProcessorEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    //public String completeProtocol() {
        //return protocol+ EndDefined.END_DEFINED_BYTES.getProtocol();
   // }

    public ProcessorEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String IdentProtocol(){
        return protocol;
    }
}
