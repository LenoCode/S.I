package S.I.socket_creation.server;

import S.I.server.socket.Server;
import S.I.server.socket.ServerConfiguration;
import S.I.server.socket_actions.server_loop.ServerWrappedLoop;
import S.I_behavior.abstractClasses.socket_managers.error_manager.error_wrapped_loop.ProgramLoopWrapper;
import S.I_behavior.interfaces.sockets.CreatedSocketModel;

public class ServerCreator {

    private ServerCreator(){
    }
    public static CreatedSocketModel createServer(
            String hostAddress,
            int port,
            int backlog,
            int timeout
    ) {
        return new CreatedSocketModel() {
            @Override
            public void runSocket(){
                ServerConfiguration serverInformation = new ServerConfiguration(hostAddress,port,backlog,timeout);

                Server server = new Server();
                server.setServerConfiguration(serverInformation);
                ServerWrappedLoop serverWrappedLoop = new ServerWrappedLoop();
                ProgramLoopWrapper.setProgrammRunning(true);
                serverWrappedLoop.activateWrappedLoop(server);
            }
            @Override
            public void closeProgram() {
                ProgramLoopWrapper.setProgrammRunning(false);
            }
        };
    }
}
