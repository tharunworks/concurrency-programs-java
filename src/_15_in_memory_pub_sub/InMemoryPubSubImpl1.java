package _15_in_memory_pub_sub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class InMemoryPubSubImpl1 implements InMemoryPubSub {

    List<String> queue = new ArrayList<>();

    ReentrantLock reentrantLock = new ReentrantLock();

    Condition hasData = reentrantLock.newCondition();

    public void put(String value){
        reentrantLock.lock();
        try {
            queue.add(value);
            System.out.println("Message: " + value);
            hasData.signalAll();
        } finally {
            reentrantLock.unlock();
        }
    }


    public String get(Integer offset){
        reentrantLock.lock();
        try {
            while (offset == queue.size()){
                try {
                    hasData.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return queue.get(offset);
        } finally {
            reentrantLock.unlock();
        }
    }

    Integer getMaxOffset(){
        reentrantLock.lock();
        try {
            return queue.size();
        } finally {
            reentrantLock.unlock();
        }
    }

}
