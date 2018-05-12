package socket_installer.SI_parts.socket_actions.recv_response;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.user_implementation.io_notification.Notificationer;
import socket_installer.SI_parts.data_carriers.response_carrier.ResponseCarrier;
import socket_installer.SI_parts.protocol.enum_protocol.BytesStatusProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.protocol_check.ProtocolCheck;

import java.io.IOException;
import java.util.Iterator;

public class ResponseHandler {

    public void parseIteratorForResponse(Iterator<String> iterator, ClientSocket clientSocket, ProtocolCheck protocolCheck) throws IOException,SocketExceptions {
        Notificationer notificationer = clientSocket.getNotificationer();

        while(iterator.hasNext()){
            String nextString = iterator.next();
            if (!protocolCheck.checkProtocol(nextString,clientSocket)){
                notificationer.notificationRecv(nextString);
            }
        }
    }


    public boolean parseIteratorForResponse(ResponseCarrier responseCarrier,Notificationer notificationer, ProtocolCheck protocolCheck){

        switch (responseCarrier.getResponseCarrierEnum()){
            case DEFINED_RESPONSE:{
                return definedResponseHandler(responseCarrier.getStringResponses());
            }
            case UNDEFINED_RESPONSE:{

                break;
            }
        }
        return false;
    }


    private boolean definedResponseHandler(Iterator<String> iterator){
        while(iterator.hasNext()){
            String next = iterator.next();
            if (next.equals(BytesStatusProtocol.BYTES_SEND_SUCCESS.completeProtocol())){
            }
        }
        return false;
    }

    private boolean bytesSendSuccessfull(String ){

    }
}
