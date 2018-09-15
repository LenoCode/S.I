package internal_tests;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import socket_installer.SI_behavior.abstractClasses.notification.notification_state_exceptions.NotificationerStatesBundle;

public class NotificationerStatesBundleTest {


    @Test
    public void checkIfKeysAreFormattedCorrectly(){
        NotificationerStatesBundle notificationerStatesBundle = new NotificationerStatesBundle();

        notificationerStatesBundle.addState("test","test1","key",200);
        notificationerStatesBundle.addState("test","test1","key1",300);
        Integer integer = notificationerStatesBundle.getState("test","test1","key");
        Integer integer1 = notificationerStatesBundle.getState("test","test1","key1");
        Assertions.assertThat(integer).isEqualTo(200);
        Assertions.assertThat(integer1).isEqualTo(300);
        notificationerStatesBundle.clearState();
        Integer nullObject = notificationerStatesBundle.getState("test","test1","key1");
        Assertions.assertThat(nullObject).isEqualTo(null);
    }
}
