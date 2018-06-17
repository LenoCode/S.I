package socket_installer.SI_parts.IO.communication_processor.processor_enums;

public enum ProcessorEnums {
    INITILIAZED,
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
    STREAM_CLOSING,
    STREAM_CLOSED,
    STREAM_CONNECTION_LOST,

    SOCKET_CLOSED,
    ;

    public static ProcessorEnums increaseProccesorCount(ProcessorEnums processorsEnums){
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
