package socket_installer.SI_parts.actionHolder;

import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;


public class ActionHolder {

    private final ReadStatusProcessorModel readStatusProcessorModel;


    public ActionHolder(ReadStatusProcessorModel readStatusProcessorModel){
        this.readStatusProcessorModel = readStatusProcessorModel;

    }

    public ReadStatusProcessorModel getReadStatusProcessorModel() {
        return readStatusProcessorModel;
    }
}
