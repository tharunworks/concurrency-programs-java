package _07_producer_consumer_without_blocking_queue;

public class Producer implements Runnable{

    MyBlockingQueue<Item> itemBlockingQueue;

    public Producer(MyBlockingQueue<Item> itemBlockingQueue) {
        this.itemBlockingQueue = itemBlockingQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Item addedItem = new Item(Thread.currentThread().getName());
                this.itemBlockingQueue.add(addedItem);
                System.out.println("addedItem.itemId: " + addedItem.itemId + ", addedItem.threadName: " + Thread.currentThread().getName());
            }catch (IllegalStateException e){
                System.out.println("Queue is full");
            }
        }
    }

}
