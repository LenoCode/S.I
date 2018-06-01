package junit.tests.statics.static_objects.thread_communicators;

public class ThreadCounterCommunicator {
    private static ThreadCounterCommunicator threadCounterCommunicator;

    private int counter;
    private ThreadCounterCommunicator(){
        counter = 0;
    }
    public static ThreadCounterCommunicator getThreadCounterCommunicator(){
        if (threadCounterCommunicator == null){
            threadCounterCommunicator = new ThreadCounterCommunicator();
        }
        return threadCounterCommunicator;
    }

    public void increase(){
        ++counter;
    }

    public int getCounter() {
        return counter;
    }

    public void finish(){
        threadCounterCommunicator = null;
    }
}
