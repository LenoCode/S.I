package socket_installer.SI_parts.protocol.enum_protocol;

public enum BytesStatusProtocol {
    BYTES_SEND_SUCCESS("<BYTES_STATUS>Bytes send success",true),
    BYTES_SEND_FAILED("<BYTES_STATUS>Bytes send failed",false)
    ;

    private String protocol;
    private boolean status;

    BytesStatusProtocol(String protocol,boolean status){
        this.protocol = protocol;
        this.status = status;
    }

    public String completeProtocol() {
        return protocol+ GeneralProtocol.END_DEFINED_BYTES.getProtocol();
    }

    public String IdentProtocol(){
        return protocol;
    }

    public boolean getStatus() {
        return status;
    }
}
