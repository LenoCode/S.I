package socket_installer.SI_parts.socket_actions.recv_response.defined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolDefinedModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.DefinedProtocol;
import socket_installer.SI_parts.socket_actions.recv_response.string_buffer.StringBuffer;

import java.util.Iterator;

public class DefinedProtocolActions {

    public ProcessorsEnums protocolDefined(StringBuffer stringBuffer){
        String string = stringBuffer.getString();
        Iterator<ProtocolDefinedModel> protocolDefinedModelIterator = DefinedProtocol.getDefinedProtocol().getDefinedProtocolIterator();

        //ITERATOR TREBA RESETIRATI,STO ZNACI DA TREBAM IMATE REFERENCU NA ARRAY LIST A NE NA ITERATOR
        while(protocolDefinedModelIterator.hasNext()){
            ProtocolDefinedModel protocolDefinedModel =protocolDefinedModelIterator.next();
            ProcessorsEnums processorsEnum;

            if ((processorsEnum=checkProtocolModel(string,protocolDefinedModel,stringBuffer)) != null){
                return processorsEnum;
            }
        }
        return null;
    }
    private ProcessorsEnums checkProtocolModel(String string,ProtocolDefinedModel protocolDefinedModel,StringBuffer stringBuffer){
        String protocolDefinedModelString = protocolDefinedModel.completeProtocol();
        System.out.println(protocolDefinedModelString + "              " +string);
        if (string.length() == protocolDefinedModelString.length() && string.equals(protocolDefinedModelString)){
            //TREBA TESTIRATI DA VIDIMO DA LI JE NABOLJE OVDJE POCISTITI STRING BUFFER
            stringBuffer.emptyBuffer();
            System.out.println("Prode dobro je" + protocolDefinedModel.getProccessorEnum());
            return protocolDefinedModel.getProccessorEnum();
        }
        else if (string.length() > protocolDefinedModelString.length()){
            System.out.println("Tu ne bi trebao uci");
            if (stripStringForStatus(string,protocolDefinedModelString,stringBuffer)){
                return protocolDefinedModel.getProccessorEnum();
            }
        }
        return null;
    }

    private boolean stripStringForStatus(String string,String protocolDefinedModelString,StringBuffer stringBuffer){
        if (string.contains(protocolDefinedModelString)){
            String rest = string.substring(protocolDefinedModelString.length());
            stringBuffer.emptyBuffer();
            stringBuffer.insertToBuffer(rest.length(),rest.getBytes());
            return true;
        }
        return false;
    }



}
