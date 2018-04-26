package socket_installer.SI_parts.protocol.enum_protocol;

public enum Protocol {
    SEND_MESSAGE_PROTOCOL("<SEND_MESSAGE>%s</SEND_MESSAGE>"),
    END_OF_PROTOCOL("</ENDPROTOCOL>")
    ;


    private String protocol;

    Protocol(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }
}
