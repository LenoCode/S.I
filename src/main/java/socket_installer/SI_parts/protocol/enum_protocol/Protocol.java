package socket_installer.SI_parts.protocol.enum_protocol;

public enum Protocol {
    END_PROTOCOL("</END(PROTOCOL_VERSION_'1')>"),
    SEND_STRING_PROTOCOL("<SEND_MESSAGE>%s"),
    SOCKET_CLOSED("<SOCKET_CLOSED>Socket closed"),
    ;

    private String protocol;

    Protocol(String protocol){
        this.protocol = protocol;
    }

    public String completeProtocol() {
        return protocol+END_PROTOCOL.protocol;
    }

    public String IdentProtocol(){
        return protocol;
    }
}