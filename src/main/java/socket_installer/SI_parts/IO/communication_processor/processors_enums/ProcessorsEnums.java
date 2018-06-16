package socket_installer.SI_parts.IO.communication_processor.processors_enums;

public enum  ProcessorsEnums {

    INITILIAZED,
    REINITILIAZED,
    FIRST_TRY,
    SECOND_TRY,
    THIRD_TRY,
    FOURTH_TRY,
    FIFTH_TRY,

    BYTES_SENT_SUCCESS,
    BYTES_SENT_FALIED,

    DATA_INCOMPLETE,
    DATA_COMPLETE,
    DATA_LINE_COMPLETE,

    STREAM_OPEN,
    STREAM_CLOSED,
    STREAM_CONNECTION_LOST,

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
            case THIRD_TRY:
                return FOURTH_TRY;
            case FOURTH_TRY:
                return FIFTH_TRY;
            default:
                return SOCKET_CLOSED;
        }
    }
}
