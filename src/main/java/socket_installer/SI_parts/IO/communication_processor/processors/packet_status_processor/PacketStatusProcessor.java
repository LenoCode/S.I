package socket_installer.SI_parts.IO.communication_processor.processors.packet_status_processor;

import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.sockets.io_models.stream_wrapper_models.InputStreamWrapperModel;
import socket_installer.SI_parts.IO.communication_processor.processors_enums.ProcessorsEnums;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.holder.packet_holder.PacketHolder;
import socket_installer.SI_parts.exception.client.connection_break_exception.ClientClosedException;
import socket_installer.SI_parts.exception.server.connection_break_exception.ConnectedClientTimeoutException;
import socket_installer.SI_parts.actionHolder.ActionHolder;
import socket_installer.SI_parts.actionHolder.actions.buffer_checker.BufferChecker;
import socket_installer.SI_parts.actionHolder.actions.string_buffer.StringBuffer;

import java.io.IOException;

public class PacketStatusProcessor {


    public void checkPacketStatus(PacketHolder packetHolder) throws IOException, SocketExceptions {
        //OVA INICIJALIZACIJA SE CINI UZASNO NEPOTREBNA,TREBA NACI NEKI DRUGI NACIN
        ClientSocket clientSocket = packetHolder.getClientSocket();
        IOHolder ioHolder = clientSocket.getIOHolder();
        ActionHolder actionHolder = clientSocket.getActions();
        InputStreamWrapperModel inputStreamWrapperModel = ioHolder.getInputStream();
        byte[] bytes = ioHolder.getBytes();
        StringBuffer stringBuffer = ioHolder.getStringBuffer();
        BufferChecker bufferChecker = actionHolder.getBufferChecker();

        try{
            inputStreamWrapperModel.read(bytes,stringBuffer);
            ProcessorsEnums status = bufferChecker.checkStringBuffer(stringBuffer);
            packetHolder.setPacketStatus(status);
        } catch (ConnectedClientTimeoutException socketTimeoutException){
            packetHolder.setPacketStatus(ProcessorsEnums.increaseProccesorCount(packetHolder.getPacketStatus()));
        } catch (ClientClosedException clientClosedException){
            packetHolder.setPacketStatus(ProcessorsEnums.SOCKET_CLOSED);
        }catch(IOException ioException){
            throw ioException;
        }

    }

}
