package socket_installer.SI_behavior.abstractClasses.notification.data_trade;


import socket_installer.SI.client.socket.ConnectedClient;
import socket_installer.SI_behavior.abstractClasses.io.communication_processor.main_processor.MainProcessor;
import socket_installer.SI_behavior.abstractClasses.sockets.socket.client.ClientSocket;
import socket_installer.SI_behavior.abstractClasses.sockets.socket_managers.error_manager.exceptions.SocketExceptions;
import socket_installer.SI_behavior.interfaces.communication_processor.read_processor.ReadStatusProcessorModel;
import socket_installer.SI_behavior.interfaces.notification.DataAsyncHandler;
import socket_installer.SI_behavior.interfaces.notification.DataTradeModel;
import socket_installer.SI_context.external_context.ExternalContext;
import socket_installer.SI_parts.IO.communication_processor.CommunicationProcessor;
import socket_installer.SI_parts.IO.communication_processor.processor_enums.ProcessorEnums;
import socket_installer.SI_parts.notification.data_trade.data_observers.DataObservers;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.SignalProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class DataTrade implements DataTradeModel {
    private ClientSocket clientSocket;
    private ExternalContext externalContext;
    private DataObservers dataObservers;
    private final MainProcessor mainProcessor = CommunicationProcessor.MainProcessor();
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final byte[] buffer = new byte[500];
    private final String SIZE_OF_DOWNLOAD = "Size:%sN";

    @Override
    public void setClientSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public ClientSocket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void injectExternalContext(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }
    @Override
    public ExternalContext getExternalContext() {
        return externalContext;
    }

    @Override
    public void resetExternalContext() {
        externalContext.clearContextObjects();
        externalContext = null;
    }

    @Override
    public DataObservers initDataObservers(Long... ids) {
        dataObservers = new DataObservers();
        for (Long id : ids){
            dataObservers.addObserver(id);
        }
        return dataObservers;
    }

    @Override
    public void sendNotification(String classIdent, String methodIdent, String data) throws IOException, SocketExceptions {
        try{
            mainProcessor.sendNotification(clientSocket,classIdent,methodIdent,data);
        }catch (IOException|SocketExceptions exception){
            if (clientSocket instanceof ConnectedClient){
                ReadStatusProcessorModel readStatusProcessorModel = clientSocket.getActions().getReadStatusProcessorModel();
                readStatusProcessorModel.setCheckReadStatus(ProcessorEnums.increaseProccesorCount(readStatusProcessorModel.checkReadStatus()));
            }
        }
    }
    @Override
    public void closeStream() throws IOException, SocketExceptions {
        mainProcessor.sendData(clientSocket,TechnicalProtocol.SOCKET_STREAM_CLOSING.completeProtocol().getBytes());
    }

    @Override
    public void sendSignal(char signal) throws IOException, SocketExceptions {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) signal;
        mainProcessor.sendData(clientSocket,bytes);
    }


    @Override
    public void sendSignal(String signal) throws IOException, SocketExceptions {
        mainProcessor.sendData(clientSocket,signal.getBytes());
    }

    @Override
    public char waitForSignal() throws IOException, SocketExceptions {
        byte[] signal = new byte[1];
        mainProcessor.readingBytesFromStream(clientSocket,signal);
        return (char)signal[0];
    }

    @Override
    public String waitForLongSignal(int signalSize) throws IOException, SocketExceptions {
        byte[] signal = new byte[signalSize];
        int bytesRead = CommunicationProcessor.MainProcessor().readingBytesFromStream(clientSocket,signal);
        return new String(signal,0,bytesRead);
    }

    @Override
    public String waitForLongSignal(byte[] signalBuffer) throws IOException, SocketExceptions {
        int bytesRead = CommunicationProcessor.MainProcessor().readingBytesFromStream(clientSocket,signalBuffer);
        return new String(signalBuffer,0,bytesRead);
    }


    @Override
    public void sendSizeOfDownload(int size) throws IOException, SocketExceptions {
        String sizeString =String.format(SIZE_OF_DOWNLOAD,size);
        upload(sizeString.getBytes());
    }

    @Override
    public byte[] download() throws IOException, SocketExceptions {
        long size = getDownloadSize();
        downloadBytes(size);
        byte[] bytesDownloaded = byteArrayOutputStream.toByteArray();
        resetByteArrayOutputStream();
        return bytesDownloaded;
    }

    @Override
    public byte[] download(int bytesSize) throws IOException, SocketExceptions {
        downloadBytes(bytesSize);
        byte[] bytesDownloaded = byteArrayOutputStream.toByteArray();
        resetByteArrayOutputStream();
        return bytesDownloaded;
    }

    @Override
    public void upload(byte[] bytes) throws IOException, SocketExceptions {
        CommunicationProcessor.MainProcessor().sendData(clientSocket,bytes);
    }

    @Override
    public void asyncDataListen(DataAsyncHandler dataAsyncHandler) throws IOException, SocketExceptions {
        int bytesRead;
        while(clientSocket.getSocketConfiguration().isSocketOnline()){
            bytesRead = mainProcessor.readingBytesFromStream(clientSocket,buffer);

            if (checkIfBufferEndsWithSeperator(bytesRead)){
                dataAsyncHandler.handleData(dataObservers,new String(buffer,0,bytesRead).split("|"));
            }
        }
    }





    public void disconnectFromServer(){
        if (clientSocket instanceof ConnectedClient){
            ((ConnectedClient)clientSocket).removeFromSessionTracker();
        }
    }

    private long getDownloadSize() throws IOException, SocketExceptions {
        char end = '/';
        while (end != 'N'){
            int bytesRead = mainProcessor.readingBytesFromStream(clientSocket,buffer);
            byteArrayOutputStream.write(buffer,0,bytesRead);
            end = (char) buffer[bytesRead - 1];
        }
        sendSignal(SignalProtocol.SIGNAL_DOWNLOAD_SIZE_RECIEVED.getProtocol());
        String size = new String(byteArrayOutputStream.toByteArray());
        int indexStart = size.indexOf(":");
        int indexEnd = size.indexOf('N');
        resetByteArrayOutputStream();
        return Long.parseLong(size.substring(indexStart + 1,indexEnd));
    }
    private void downloadBytes(long size) throws IOException, SocketExceptions {
        while(size != 0){
            int bytesRead = mainProcessor.readingBytesFromStream(clientSocket,buffer);
            byteArrayOutputStream.write(buffer,0,bytesRead);
            size -= bytesRead;
        }
    }
    private boolean checkIfBufferEndsWithSeperator(int bytesRead){
        if (buffer[bytesRead-1] == '|'){
            return true;
        }
        return false;
    }

    private void resetByteArrayOutputStream(){
        byteArrayOutputStream.reset();
    }
}
