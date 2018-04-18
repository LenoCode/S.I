package S.I_behavior.non_abstract_classes.io_components;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

import S.I_behavior.abstractClasses.socket_managers.error_manager.exceptions.SocketExceptions;
import S.I_behavior.non_abstract_classes.exceptions.SocketClosedExceptions;


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
            System.out.println(ioHolder);
            byte[] bytes = ioHolder.getBytes();
            StringBuffer buffer = ioHolder.getStringBuffer();
            BufferedInputStream inputStream = ioHolder.getInStream();
            bytesRead = -1;
            bytesRead = inputStream.read(bytes);

        }catch (IOException ioException){
            if (bytesRead ==  -1){
                throw new SocketClosedExceptions();
            }

        }
    }

}
