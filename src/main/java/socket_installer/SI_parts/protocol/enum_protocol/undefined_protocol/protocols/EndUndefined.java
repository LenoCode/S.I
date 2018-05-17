package socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols;

public enum EndUndefined {

    END_UNDEFINED_BYTES("</END UNDEFINED(PROTOCOL_VERSION_'1')>"),
    ;

    private String protocol;

    EndUndefined(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol(){
        return protocol;
    }
}
