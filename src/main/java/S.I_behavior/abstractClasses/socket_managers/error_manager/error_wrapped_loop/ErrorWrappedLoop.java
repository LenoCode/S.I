package S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop;

import S.I_behavior.interfaces.socket_managers.error_manager.WrappedLoopModel;

public abstract class ErrorWrappedLoop implements WrappedLoopModel {
    private static boolean programmRunning;

    public static void setProgrammRunning(boolean status){
        programmRunning = status;
    }
    public static boolean isProgramRunning(){
        return programmRunning;
    }
}