package socket_installer.SI_behavior.abstractClasses.sockets.socket_actions.socket_loop;

import socket_installer.SI_behavior.interfaces.socket_managers.error_manager.WrappedLoopModel;

public abstract class ProgramLoopWrapper implements WrappedLoopModel {
    private static boolean programmRunning;

    public static void setProgrammRunning(boolean status){
        programmRunning = status;
    }
    public static boolean isProgramRunning(){
        return programmRunning;
    }
}