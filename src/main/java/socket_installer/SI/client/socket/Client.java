package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.main_processors.ClientMainProcessor;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;

import socket_installer.SI_parts.IO.wrapper.client.ClientInputStreamWrapper;
import socket_installer.SI_parts.IO.wrapper.client.ClientOutputStreamWrapper;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.actionHolder.ActionHolder;
import socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.client_impl.ClientReadStatusProcessor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client extends ClientSocket {

    public Client(Socket clientSocket) {
        super(clientSocket);
        actions = new ActionHolder(new ClientReadStatusProcessor());
    }

    @Override
    public void activateSocket(String classIdent,String methodIdent,String notification) throws IOException, SocketExceptions {
        System.out.println(classIdent +"  "+methodIdent);
        ClientMainProcessor communicationProcessor = CommunicationProcessor.getClientCommunicationProcessor();
        communicationProcessor.openStreamSocket(this);
        communicationProcessor.sendNotification(this, classIdent, methodIdent, notification);
        communicationProcessor.readingDataFromStream(this);
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
    }

    public void reconnectSocket() throws IOException, SocketExceptions{
        deactivateSocket();
        Socket socket = new Socket(socketConfiguration.getIpAddress(),socketConfiguration.getPort());
        replaceSocket(socket);
    }

    private void setupStream(Socket socket) throws IOException, SocketExceptions{
        ioHolder.setInputStream(new ClientInputStreamWrapper( this,socket.getInputStream() ));
        ioHolder.setOutputStream(new ClientOutputStreamWrapper( this,socket.getOutputStream() ));
    }
}
