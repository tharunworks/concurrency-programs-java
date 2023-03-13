package _15_in_memory_pub_sub;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProducerConsumer {

    private final Queue<String> buffer = new LinkedList<>();
    private final int capacity = 5;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Condition bufferNotEmpty = lock.readLock().newCondition();
    private final Condition bufferNotFull = lock.writeLock().newCondition();

    public void produce(String message) throws InterruptedException {
        lock.writeLock().lock();
        try {
            while (buffer.size() == capacity) {
                bufferNotFull.await();
            }
            buffer.add(message);
            bufferNotEmpty.signalAll();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.readLock().lock();
        try {
            while (buffer.isEmpty()) {
                bufferNotEmpty.await();
            }
            String message = buffer.poll();
            bufferNotFull.signalAll();
            return message;
        } finally {
            lock.readLock().unlock();
        }
    }
}

