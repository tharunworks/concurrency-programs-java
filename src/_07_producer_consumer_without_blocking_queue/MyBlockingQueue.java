package _07_producer_consumer_without_blocking_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    Queue<T> queue = new LinkedList<>();
    int maxQueueSize = 10;
    ReentrantLock lock = new ReentrantLock();

    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();
    void add(T item){
        lock.lock();
        try {
            while (queue.size() == maxQueueSize) {
//                handle when queue is full
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.add(item);
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    T remove(){
        lock.lock();
        try {
            while (queue.isEmpty()){
//           handle when queue is empty
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T removedItem = queue.remove();
            notFull.signalAll();
            return (T) removedItem;
        }finally {
            lock.unlock();
        }
    }

}
