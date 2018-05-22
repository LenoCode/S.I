package socket_installer.SI_behavior.abstractClasses.user_implementation.notificationer;


import socket_installer.SI_behavior.abstractClasses.io.communication_processor.packet_processor.PacketProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketRequest;
import socket_installer.SI_parts.protocol.enum_protocol.undefined_protocol.protocols.ClientProtocol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Notificationer<A>{

    private ClientSocket clientSocket;
    private final A[] objects;

    protected Notificationer(A[] methods) {
        this.objects = methods;
    }

    public final void sendMessageNotificationerDefault(String message) throws IOException,SocketExceptions {
        message = String.format(ClientProtocol.SEND_MESSAGE.completeProtocol(),message);
        PacketRequest packetHolder = new PacketRequest(clientSocket,message);
        PacketProcessor.getPacketProcessor(clientSocket).sendPacket(packetHolder);
    }

    public final void setClientSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }


    public final void callAppropriateMethod(String methodToCall){
        for (A object : objects){
            callMethod(object,methodToCall);
        }
    }

    private final void callMethod(A object,String methodToCall){
        for (Method method : object.getClass().getMethods()){
            String nameOfMethod = method.getName();
            if (nameOfMethod.equals(methodToCall)){
                try {
                    method.invoke(object,methodToCall);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
