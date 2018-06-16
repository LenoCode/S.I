package socket_installer.SI_parts.IO.communication_processor;



import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_parts.IO.communication_processor.main_processors.ClientMainProcessor;
import socket_installer.SI_parts.IO.communication_processor.main_processors.ConnectedClientMainProcessor;



public class CommunicationProcessor {
    private static CommunicationProcessor communicationProcessor;
    private ClientMainProcessor clientMainProcessor;
    private ConnectedClientMainProcessor connectedClientMainProcessor;

    private CommunicationProcessor(){
        clientMainProcessor = new ClientMainProcessor();
        connectedClientMainProcessor = new ConnectedClientMainProcessor();
    }
    public static MainProcessor MainProcessor(){
        if (communicationProcessor == null){
            communicationProcessor = new CommunicationProcessor();
        }
        return communicationProcessor.clientMainProcessor;
    }

    public static ClientMainProcessor getClientCommunicationProcessor(){
        if (communicationProcessor == null){
            communicationProcessor = new CommunicationProcessor();
        }
        return communicationProcessor.clientMainProcessor;
    }
    public static ConnectedClientMainProcessor getConnectedClientCommunicationProcessor(){
        if (communicationProcessor == null){
            communicationProcessor = new CommunicationProcessor();
        }
        return communicationProcessor.connectedClientMainProcessor;
    }

}
