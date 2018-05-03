package socket_installer.SI_parts.protocol.enum_protocol;

public enum TehnicalProtocol {
    SOCKET_CLOSED("<SOCKET_CLOSED>Socket closed"),
    ;

    private String protocol;

    TehnicalProtocol(String protocol){
        this.protocol = protocol;
    }

    public String completeProtocol() {
        return protocol+ GeneralProtocol.END_BYTES.getProtocol();
    }

    public String IdentProtocol(){
        return protocol;
    }
}