package socket_installer.SI_parts.protocol.enum_protocols.general_protocols;

public enum SignalProtocol {

    SIGNAL_READY_FOR_DOWNLOAD('@'),
    SIGNAL_DOWNLOAD_SIZE_RECIEVED('^'),
    SIGNAL_DOWNLOAD_DONE('X'),
    SIGNAL_SEPERATOR('&'),
    SIGNAL_PROBLEM_OCCURED('!');
    ;

    private char protocol;

    SignalProtocol(char protocol){
        this.protocol = protocol;
    }

    public char getProtocol() {
        return protocol;
    }
}
