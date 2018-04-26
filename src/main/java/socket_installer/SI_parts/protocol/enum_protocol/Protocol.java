package socket_installer.SI_parts.protocol.enum_protocol;

public enum Protocol {
    END_PROTOCOL("</END(PROTOCOL_VERSION_'1')>"),
    SEND_STRING_PROTOCOL("<SEND_MESSAGE>%s"+END_PROTOCOL.getProtocol()),
    ;

    private String protocol;

    Protocol(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }
}
