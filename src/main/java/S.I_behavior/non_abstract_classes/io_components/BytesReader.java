package S.I_behavior.non_abstract_classes.io_components;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.non_abstract_classes.exceptions.SocketClosedException;
import S.I_behavior.non_abstract_classes.exceptions.SocketStreamTimeoutException;


public class BytesReader {

    private static BytesReader bytesReader;

    private BytesReader(){
    }

    public static BytesReader getBytesReader(){
        if (bytesReader == null){
            bytesReader = new BytesReader();
        }
        return bytesReader;
    }


    public void readBytes(IOHolder ioHolder)throws IOException, SocketExceptions {
        int bytesRead = 0;
        try{
            byte[] bytes = ioHolder.getBytes();
            StringBuffer buffer = ioHolder.getStringBuffer();
            BufferedInputStream inputStream = ioHolder.getInStream();

            bytesRead = inputStream.read(bytes);
            buffer.insertToBuffer(bytesRead,bytes);

        } catch (SocketTimeoutException socketTimeoutException){
            throw new SocketStreamTimeoutException();
        } catch (IOException ioException){
            switch (bytesRead){
                case -1:
                    throw new SocketClosedException();
                default:
                    throw new IOException(ioException);
            }
        }
    }

}
