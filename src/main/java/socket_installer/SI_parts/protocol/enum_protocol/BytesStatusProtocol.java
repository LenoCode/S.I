package socket_installer.SI_parts.protocol.enum_protocol;

public enum BytesStatusProtocol {
    SEND_BYTES("<SEND_MESSAGE>%s"),
    BYTES_SEND_SUCCESS("<BYTES_STATUS>Bytes send success"),
    BYTES_SEND_FAILED("<BYTES_STATUS>Bytes send failed")
    ;

    private String protocol;

    BytesStatusProtocol(String protocol){
        this.protocol = protocol;
    }

    public String completeProtocol() {
        return protocol+ GeneralProtocol.END_BYTES.getProtocol();
    }

    public String IdentProtocol(){
        return protocol;
    }
}
