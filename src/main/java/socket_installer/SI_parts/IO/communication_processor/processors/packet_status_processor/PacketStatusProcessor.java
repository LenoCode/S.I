package socket_installer.SI_parts.IO.communication_processor.processors.packet_status_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.OutputStreamWrapperModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientConnectionAbortException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;
import socket_installer.SI_parts.actionHolder.ActionHolder;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;

import java.io.IOException;

public class PacketStatusProcessor {


    public void checkReadPacketStatus(PacketHolder packetHolder) throws IOException, SocketExceptions {
        ClientSocket clientSocket = packetHolder.getClientSocket();
        IOHolder ioHolder = clientSocket.getIOHolder();
        ActionHolder actionHolder = clientSocket.getActions();
        InputStreamWrapperModel inputStreamWrapperModel = ioHolder.getInputStream();
        byte[] bytes = ioHolder.getBytes();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();


        try{
            inputStreamWrapperModel.read(bytes,stringBuffer);
        } catch (ConnectedClientTimeoutException socketTimeoutException){
            packetHolder.setPacketStatus(ProcessorsEnums.increaseProccesorCount(packetHolder.getPacketStatus()));
        } catch (ClientClosedException clientClosedException){
            packetHolder.setPacketStatus(ProcessorsEnums.SOCKET_CLOSED);
        }catch(IOException ioException){
            throw ioException;
        }
    }

    public void checkSendPacketStatus(PacketHolder packetHolder,String message) throws IOException,SocketExceptions{
        try{
            OutputStreamWrapperModel outputStreamWrapperModel = packetHolder.getClientSocket().getIOHolder().getOutputStream();
            outputStreamWrapperModel.send(message.getBytes());
        }catch (ClientConnectionAbortException clientAbortException){
            packetHolder.setPacketStatus(ProcessorsEnums.increaseProccesorCount(packetHolder.getPacketStatus()));
        }
    }

}
