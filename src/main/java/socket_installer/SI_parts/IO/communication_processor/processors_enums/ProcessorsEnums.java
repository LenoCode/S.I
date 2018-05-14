package socket_installer.SI_parts.IO.communication_processor.processors_enums;

public enum  ProcessorsEnums {

    INITILIAZED,
    FIRST_TRY,
    SECOND_TRY,
    THIRD_TRY,

    BYTES_SENT_SUCCESS,
    BYTES_SENT_FALIED,


    DATA_RECV_FAILED,
    DATA_INCOMPLETE,
    DATA_COMPLETE,

    SOCKET_CLOSED,
    ;

    public static ProcessorsEnums increaseProccesorCount(ProcessorsEnums processorsEnums){
        switch (processorsEnums){
            case INITILIAZED:
                return FIRST_TRY;
            case FIRST_TRY:
                return SECOND_TRY;
            case SECOND_TRY:
                return THIRD_TRY;
            default:
                return SOCKET_CLOSED;
        }
    }
}
