package socket_installer.SI_parts.protocol.enum_protocols.data_protocol;

import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;




public enum DataProtocol {
    DATA_STRING("<DATA_STRING>classIdent:%s|methodIdent:%s|message:%s",ProcessorEnums.DATA_COMPLETE),
    ;

    private String protocol;
    private ProcessorEnums processorsEnums;

    DataProtocol(String protocol,ProcessorEnums processorsEnums){
        this.protocol = protocol;
        this.processorsEnums = processorsEnums;
    }

    public String completeProtocol() {
        return protocol+ EndMarkerProtocol.END_LINE.getProtocol();
    }

    public ProcessorEnums getProccessorEnum() {
        return processorsEnums;
    }

    public String IdentProtocol(){
        return protocol;
    }


    public static String sendMessageFormat(String classIdent,String methodIdent,String message){
        String completeProtocol = DATA_STRING.completeProtocol();
        return String.format(completeProtocol,classIdent,methodIdent,message);
    }
}
