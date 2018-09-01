package internal_tests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import socket_installer.SI_parts.protocol.enum_protocols.general_protocols.EndMarkerProtocol;

public class RegexTests {
    private final String example = "<DATA_STRING>classIdent:ExtraDataInBufferClassIdent|methodIdent:ExtraDataInBufferClient|message:server</END LINE><SOCKET_STREAM_CLOSING>Stream closing></END TRANSFER>Finished>";

    @Test
    public void checkIfEndLineRegexWorks(){
        String[] afterSplit = example.split(EndMarkerProtocol.getEndRegex());
        String dataString = "<DATA_STRING>classIdent:ExtraDataInBufferClassIdent|methodIdent:ExtraDataInBufferClient|message:server";
        String closingString = "<SOCKET_STREAM_CLOSING>Stream closing>";
        Assertions.assertThat(afterSplit[0].equals(dataString));
        Assertions.assertThat(afterSplit[1].equals(closingString));

        for (String string : afterSplit){
            System.out.println(string);
        }
    }
}
