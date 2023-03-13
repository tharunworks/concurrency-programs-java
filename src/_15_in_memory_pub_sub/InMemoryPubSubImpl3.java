package _15_in_memory_pub_sub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InMemoryPubSubImpl3 implements InMemoryPubSub {

    List<String> queue = new ArrayList<>();

    ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();

    Condition hasData = reentrantLock.writeLock().newCondition();

//    Lock readLock = reentrantLock.readLock();
//    Lock writeLock = reentrantLock.writeLock();

    public void put(String value){
        try {
            reentrantLock.writeLock().lockInterruptibly();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            queue.add(value);
            System.out.println("Message: " + value);
            hasData.signalAll();
        } finally {
            reentrantLock.writeLock().unlock();
        }
    }


    public String get(Integer offset){
        reentrantLock.readLock().lock();
        try {
            if(offset == queue.size()){
                reentrantLock.readLock().unlock();
                reentrantLock.writeLock().lock(); // need to lock the writeLock to allow to use the condition.
                // only one reader will get the lock, other readers will wait here
                try{
                    while(offset == queue.size()) // check if there' still no data
                    {
                        try {
                            hasData.await(); //will unlock and re-lock after writer has signalled and unlocked.
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    reentrantLock.readLock().lock();    // continue blocking writer
                }
                finally
                {
                    reentrantLock.writeLock().unlock(); //let other readers in
                }
            }
            return queue.get(offset);
        } finally {
            reentrantLock.readLock().unlock();
        }
    }

    Integer getMaxOffset(){
        reentrantLock.readLock().lock();
        try {
            return queue.size();
        } finally {
            reentrantLock.readLock().unlock();
        }
    }

}
