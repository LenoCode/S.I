package socket_installer.SI_parts.protocol.enum_protocols.general_protocols;


public enum  EndMarkerProtocol {

    END_LINE("</END LINE>"),
    END_TRANSFER("</END TRANSFER>Finished>");
    ;

    private String protocol;

    EndMarkerProtocol(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol(){
        return protocol;
    }
}
