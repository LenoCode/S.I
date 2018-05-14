package socket_installer.SI_parts.socket_actions.recv_response;



import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;
import socket_installer.SI_parts.protocol.enum_protocol.GeneralProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.defined_protocol.DefinedProtocolActions;
import socket_installer.SI_parts.socket_actions.recv_response.defined_protocol.UndefinedProtocolActions;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;
import socket_installer.SI_parts.socket_actions.recv_response.string_parser.StringParser;


import java.io.IOException;

public class BufferChecker {
    private static final DefinedProtocolActions definedProtocolActions = new DefinedProtocolActions();
    private static final UndefinedProtocolActions undefinedProtocolActions = new UndefinedProtocolActions();

    public BufferChecker() {

    }

    public ProcessorsEnums checkStringBuffer(StringBuffer stringBuffer) throws IOException,SocketExceptions {
       GeneralProtocol parsedStringProtocol = parseString(stringBuffer);

       return checkParsedString(parsedStringProtocol,stringBuffer);
    }
    private GeneralProtocol parseString(StringBuffer stringBuffer){
       return  StringParser.getStringParser().parseForStrings(stringBuffer.getString());
    }

    private ProcessorsEnums checkParsedString(GeneralProtocol generalProtocol,StringBuffer stringBuffer)throws IOException, SocketExceptions {

        if (generalProtocol == GeneralProtocol.DEFINED){
            return definedProtocolActions.protocolDefined(stringBuffer);
        }
        else if (generalProtocol == GeneralProtocol.UNDEFINED){
            return undefinedProtocolActions.protocolUndefined(stringBuffer);
        }
        else if (generalProtocol == GeneralProtocol.UNFINISHED_DATA){
            System.out.println("BUFFER CHECKER"+  "TU SAM UNFISNIHED DATA" + stringBuffer.getString());
            return ProcessorsEnums.DATA_INCOMPLETE;
        }
        else{
            throw new ConnectedClientTimeoutException();
        }
    }

}
