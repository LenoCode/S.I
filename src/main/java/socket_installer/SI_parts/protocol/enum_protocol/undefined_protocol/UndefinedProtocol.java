package socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolUndefinedModel;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UndefinedProtocol {
    private static UndefinedProtocol undefinedProtocol;

    private final Iterator<ProtocolUndefinedModel> iterator;

    private UndefinedProtocol(){
        List<ProtocolUndefinedModel> protocolDefinedModelList = new ArrayList<>();
        ProtocolUndefinedModel[] clientProtocol = ClientProtocol.values();

        loadIterator(protocolDefinedModelList,clientProtocol);

        iterator = protocolDefinedModelList.iterator();
    }
    private void loadIterator(List<ProtocolUndefinedModel> protocolUndefinedModelList,ProtocolUndefinedModel[] protocolUndefinedModels){
        for (ProtocolUndefinedModel model : protocolUndefinedModels){
            protocolUndefinedModelList.add(model);
        }

    }
    public static UndefinedProtocol getUndefinedProtocol(){
        if (undefinedProtocol == null){
            undefinedProtocol = new UndefinedProtocol();
        }
        return undefinedProtocol;
    }

    public Iterator<ProtocolUndefinedModel> getDefinedProtocolIterator(){
        return iterator;
    }
}
