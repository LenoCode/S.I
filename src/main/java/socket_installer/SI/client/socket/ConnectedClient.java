package socket_installer.SI.client.socket;


import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;

import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.main_processors.ConnectedClientMainProcessor;
import socket_installer.SI_parts.IO.holder.io_holder.IOHolder;
import socket_installer.SI_parts.IO.wrapper.server.ConnectedClientInputStreamWrapper;
import socket_installer.SI_parts.IO.wrapper.server.ConnectedClientOutputStreamWrapper;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.actionHolder.ActionHolder;
import socket_installer.SI_parts.actionHolder.actions.communication_processor_actions.read_processor_actions.connected_client_impl.ConnectedClientReadStatusProcessor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ConnectedClient extends ClientSocket {


    public ConnectedClient(Socket clientSocket) {
        super(clientSocket);
        actions = new ActionHolder(new ConnectedClientReadStatusProcessor());
    }

    @Override
    public void activateSocket() throws IOException, SocketExceptions {
        ClientConfiguration clientConfiguration = (ClientConfiguration) getSocketConfiguration();
        ConnectedClientMainProcessor connectedClientMainProcessor = CommunicationProcessor.getConnectedClientCommunicationProcessor();

        MainLoop:
        while(clientConfiguration.isSocketOnline()){
            System.out.println("Check if stream is open "+Thread.currentThread().getId());
            if (!actions.getReadStatusProcessorModel().checkIfStreamOpen()){
                System.out.println("Stream is not opened"+Thread.currentThread().getId());
                connectedClientMainProcessor.checkIfStreamReadyToOpen(this);
            }else{
                System.out.println("stream is opened, reading client messages ------------>");
                connectedClientMainProcessor.readingDataFromStream(this);
                System.out.println("------> reading done, no error thrown    stringBuffer : "+getIOHolder().getStringBuffer().getString());
                break MainLoop;
            }
        }
    }

    @Override
    public void setupIOHolder() throws IOException, SocketExceptions {
        Socket clientSocket = (Socket) socket;
        ioHolder = new IOHolder();
        setupStream(clientSocket);
        ioHolder.setStringBuffer(new StringBuffer());
    }

    @Override
    public void replaceSocket(Socket socket) throws IOException, SocketExceptions {
        this.socket = socket;
        System.out.println("replacing socket ...... this is what have left in buffer"+ioHolder.getStringBuffer().getString());
        ioHolder.getInputStream().replaceInputStream(socket.getInputStream());
        ioHolder.getOutputStream().replaceOutputStream(socket.getOutputStream());
        ((ClientConfiguration)socketConfiguration).setStreamPaused(false);
        socketConfiguration.setSocketOnlineStatus(true);
    }

    private void setupStream(Socket socket) throws IOException, SocketExceptions{
        ioHolder.setInputStream(new ConnectedClientInputStreamWrapper( this,socket.getInputStream() ));
        ioHolder.setOutputStream(new ConnectedClientOutputStreamWrapper( this,socket.getOutputStream() ));
    }
}

