package socket_installer_test_environment.tools.static_methods;

public class StaticMethods {

    public static void sleep(int timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void threadRun(Runnable runnable){
        Thread thread = new Thread();
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Pronasao sam error");
            }
        });
        new Thread(runnable).start();
    }

    public static int waitTurn(int waitLong){
        sleep(2000);
        return --waitLong;
    }
}
