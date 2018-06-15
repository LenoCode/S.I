package socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols;

public enum EndDefined {

    END_DEFINED_BYTES("</END DEFINED(PROTOCOL_VERSION_'1')>"),
    ;

    private String protocol;

    EndDefined(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol(){
        return protocol;
    }
}
