package junit.tests.client.client_communication;


import junit.tests.rules.ClientResource;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class ClientCommunication {

    @Rule
    public ClientResource clientResource = new ClientResource(null);

    @Test
    public void isClientConnectedToServer(){
        assertThat(clientResource.getClientCreatedSocket().isConnectedToServer()).isEqualTo(true);
    }
}
