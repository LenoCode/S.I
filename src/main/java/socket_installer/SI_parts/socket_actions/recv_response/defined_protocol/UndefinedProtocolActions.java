package socket_installer.SI_parts.socket_actions.recv_response.defined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolUndefinedModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.UndefinedProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.util.Iterator;

public class UndefinedProtocolActions {

    public ProcessorsEnums protocolUndefined(StringBuffer stringBuffer){
        String string = stringBuffer.getString();
        Iterator<ProtocolUndefinedModel> undefinedModelIterator = UndefinedProtocol.getUndefinedProtocol().getDefinedProtocolIterator();

        while(undefinedModelIterator.hasNext()){
            ProtocolUndefinedModel protocolUndefinedModel =undefinedModelIterator.next();
            ProcessorsEnums processorsEnum;

            if ((processorsEnum=checkProtocolModel(string,protocolUndefinedModel)) != null){
                return processorsEnum;
            }
        }
        return null;
    }

    private ProcessorsEnums checkProtocolModel(String string, ProtocolUndefinedModel protocolUndefinedModel) {
        String protocolDefinedModelString = protocolUndefinedModel.completeProtocol();
        int firstPartOfProtocolIndex = protocolDefinedModelString.indexOf(">")+1;

        //OVAJ DIIO TREBA ZAVRSITI, NAPRAVITI KAKO CE SE REAGIRATI KAD U JEDNOM STRINGU IMAMO VISE REQUESTA, I DALI DA TU RADIMO NOTIFICATIONR NOTIFY

        if (string.substring(0,firstPartOfProtocolIndex).equals(protocolDefinedModelString.substring(0,firstPartOfProtocolIndex))){
            return protocolUndefinedModel.getProccessorEnum();
        }
        else if (string.length() > protocolDefinedModelString.length()){
            //OVDJE TREBA NAPISATI ONU METODU SPLIT U SLUCAJU KAKO DOBIJE VISE REQUESTA U STRINGU
        }
        return null;
    }

}
