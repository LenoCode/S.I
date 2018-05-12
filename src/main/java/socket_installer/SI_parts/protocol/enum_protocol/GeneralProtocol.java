package socket_installer.SI_parts.protocol.enum_protocol;

public enum GeneralProtocol {

    END_UNDEFINED_BYTES("</END UNDEFINED(PROTOCOL_VERSION_'1')>"),
    END_DEFINED_BYTES("</END DEFINED(PROTOCOL_VERSION_'1')")
    ;

    private String protocol;

    GeneralProtocol(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol(){
        return protocol;
    }
}
