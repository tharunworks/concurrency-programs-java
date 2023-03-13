package _15_in_memory_pub_sub;

public class Consumer implements Runnable{

    InMemoryPubSubImpl2 inMemoryQueue;

    Integer offset;

    public Consumer(InMemoryPubSubImpl2 inMemoryQueue) {
        this.inMemoryQueue = inMemoryQueue;
        this.offset = 0;
    }

    @Override
    public void run() {

        while(true){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println(Thread.currentThread() + ": " + inMemoryQueue.get(offset));
            offset++;
        }

    }
}
