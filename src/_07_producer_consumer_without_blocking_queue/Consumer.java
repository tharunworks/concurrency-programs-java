package _07_producer_consumer_without_blocking_queue;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    MyBlockingQueue<Item> itemBlockingQueue;

    public Consumer(MyBlockingQueue<Item> itemBlockingQueue) {
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
