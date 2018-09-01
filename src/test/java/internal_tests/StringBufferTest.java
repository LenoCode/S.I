package internal_tests;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import socket_installer.SI_parts.IO.communication_processor.buffer_processor.BufferProcessor;
import socket_installer.SI_parts.IO.holder.string_buffer.StringBuffer;
import socket_installer.SI_parts.protocol.enum_protocols.data_protocol.DataProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;
import socket_installer.SI_parts.protocol.enum_protocols.technical_protocol.TechnicalProtocol;

public class StringBufferTest {

    private StringBuffer stringBuffer;
    private BufferProcessor bufferProcessor;
    @Before
    public void before(){
        stringBuffer = new StringBuffer();
        bufferProcessor = new BufferProcessor();
    }

//    @Test
//    public void testIfStringBufferRemoveOnlyOneNotification(){
//        String response1 = DataProtocol.sendMessageFormat("test","test1","response1");
//        String response2 = DataProtocol.sendMessageFormat("test","test1","response2");
//        stringBuffer.insertToBuffer(response1);
//        stringBuffer.insertToBuffer(response2);
//        stringBuffer.insertToBuffer(TechnicalProtocol.SOCKET_STREAM_CLOSED.completeProtocol());
//
//        String responseNotification = bufferProcessor.parseNextNotification(stringBuffer);
//        stringBuffer.removeFirstNotification(responseNotification);
//        Assertions.assertThat(response1.contains(responseNotification)).isEqualTo(true);
//        Assertions.assertThat(stringBuffer.getString().
//                equals("<DATA_STRING>classIdent:test|methodIdent:test1|message:response2</END LINE><SOCKET_STREAM_CLOSED>Stream closed></END TRANSFER>Finished>")).isEqualTo(true);
//    }





    @After
    public void after(){

    }
}
