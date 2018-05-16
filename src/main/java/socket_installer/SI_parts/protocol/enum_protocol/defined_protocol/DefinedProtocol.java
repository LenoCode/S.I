package socket_installer.SI_parts.protocol.enum_protocol.defined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolDefinedModel;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.BytesStatusProtocol;
import socket_installer.SI_parts.protocol.enum_protocol.defined_protocol.protocols.TehnicalProtocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DefinedProtocol {
    private static DefinedProtocol definedProtocol;

    private final ArrayList<ProtocolDefinedModel> protocolDefinedModelList;

    private DefinedProtocol(){
        protocolDefinedModelList = new ArrayList<>();
        ProtocolDefinedModel[] tehnicalProtocol = TehnicalProtocol.values();
        ProtocolDefinedModel[] bytesStatusProtocols = BytesStatusProtocol.values();

        loadIterator(tehnicalProtocol);
        loadIterator(bytesStatusProtocols);

    }
    private void loadIterator(ProtocolDefinedModel[] protocolDefinedModels){
        protocolDefinedModelList.addAll(Arrays.asList(protocolDefinedModels));
    }
    public static DefinedProtocol getDefinedProtocol(){
        if (definedProtocol == null){
            definedProtocol = new DefinedProtocol();
        }
        return definedProtocol;
    }

    public Iterator<ProtocolDefinedModel> getDefinedProtocolIterator(){
        return protocolDefinedModelList.iterator();
    }
}
