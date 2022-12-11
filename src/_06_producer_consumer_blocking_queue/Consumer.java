package _06_producer_consumer_blocking_queue;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    BlockingQueue<Item> itemBlockingQueue;

    public Consumer(BlockingQueue<Item> itemBlockingQueue) {
        this.itemBlockingQueue = itemBlockingQueue;
    }
    @Override
    public void run() {
        while (true){
            try {
                Item removedItem = itemBlockingQueue.remove();
                System.out.println("removedItem.itemId " + removedItem.itemId + ", removedItem.threadName" + Thread.currentThread().getName());
            }catch (NoSuchElementException e){
                System.out.println("Queue is empty");
            }
        }
    }
}
