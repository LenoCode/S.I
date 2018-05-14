package socket_installer.SI_parts.protocol.enum_protocol.defined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolDefinedModel;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.BytesStatusProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.TehnicalProtocol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefinedProtocol {
    private static DefinedProtocol definedProtocol;

    private final Iterator<ProtocolDefinedModel> iterator;

    private DefinedProtocol(){
        List<ProtocolDefinedModel> protocolDefinedModelList = new ArrayList<>();
        ProtocolDefinedModel[] tehnicalProtocol = TehnicalProtocol.values();
        ProtocolDefinedModel[] bytesStatusProtocols = BytesStatusProtocol.values();

        loadIterator(protocolDefinedModelList,tehnicalProtocol);
        loadIterator(protocolDefinedModelList,bytesStatusProtocols);

        iterator = protocolDefinedModelList.iterator();
    }
    private void loadIterator(List<ProtocolDefinedModel> protocolDefinedModel,ProtocolDefinedModel[] protocolDefinedModels){
        for (ProtocolDefinedModel model : protocolDefinedModels){
            protocolDefinedModel.add(model);
        }

    }
    public static DefinedProtocol getDefinedProtocol(){
        if (definedProtocol == null){
            definedProtocol = new DefinedProtocol();
        }
        return definedProtocol;
    }

    public Iterator<ProtocolDefinedModel> getDefinedProtocolIterator(){
        return iterator;
    }
}
