package socket_installer.SI.socket_creation.thread_creator.thread_processor_enums;

public enum ThreadProcessorEnum {
    CLIENT_THREAD_IN_PROCESS("clTInProcces"),
    WAITING_CLIENT_RECONNECT("waiClReconnect"),
    CLIENT_RECONNECT("clReconnect"),

    ;


    private String id;

    ThreadProcessorEnum(String id){
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
