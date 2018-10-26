package socket_installer.SI_parts.notification.data_trade.data_observers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataObservers {
    private final Map<Long, ConcurrentLinkedQueue<String>> observers;

    public DataObservers() {
        this.observers = new HashMap<>();
    }

    public void addObserver(Long id){
        this.observers.put(id,new ConcurrentLinkedQueue<>());
    }
    public boolean checkForData(Long id){
        ConcurrentLinkedQueue<String> queue = observers.get(id);
        if (queue.size() > 0){
            return true;
        }
        return false;
    }
    public String getData(Long id){
        ConcurrentLinkedQueue<String> queue = observers.get(id);
        if (queue.size() > 0){
            return queue.poll();
        }
        return null;
    }
    public void addData(Long id,String data){
        observers.get(id).offer(data);
    }

}
