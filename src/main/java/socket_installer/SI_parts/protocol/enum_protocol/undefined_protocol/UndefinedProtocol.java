package socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol;

import socket_installer.SI_behavior.interfaces.enums.IO_enums.protocol.ProtocolUndefinedModel;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class UndefinedProtocol {
    private static UndefinedProtocol undefinedProtocol;

    private final ArrayList<ProtocolUndefinedModel> protocolUndefinedModelList;

    private UndefinedProtocol(){
        protocolUndefinedModelList = new ArrayList<>();
        ProtocolUndefinedModel[] clientProtocol = ClientProtocol.values();

        loadIterator(clientProtocol);
    }
    private void loadIterator(ProtocolUndefinedModel[] protocolUndefinedModels){
        protocolUndefinedModelList.addAll(Arrays.asList(protocolUndefinedModels));

    }
    public static UndefinedProtocol getUndefinedProtocol(){
        if (undefinedProtocol == null){
            undefinedProtocol = new UndefinedProtocol();
        }
        return undefinedProtocol;
    }

    public Iterator<ProtocolUndefinedModel> getDefinedProtocolIterator(){
        return protocolUndefinedModelList.iterator();

    }
}
