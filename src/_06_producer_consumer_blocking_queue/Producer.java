package _06_producer_consumer_blocking_queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    BlockingQueue<Item> itemBlockingQueue;

    public Producer(BlockingQueue<Item> itemBlockingQueue) {
        this.itemBlockingQueue = itemBlockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Item addedItem = new Item();
                this.itemBlockingQueue.add(addedItem);
                System.out.println("addedItem.itemId " + addedItem.itemId + ", addedItem.threadName" + Thread.currentThread().getName());
            }catch (IllegalStateException e){
                System.out.println("Queue is full");
            }
        }
    }

}
