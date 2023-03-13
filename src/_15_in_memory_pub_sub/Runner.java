package _15_in_memory_pub_sub;

public class Runner {
    void init(){
        InMemoryPubSubImpl2 inMemoryQueue = new InMemoryPubSubImpl2();
        Thread producer1 = new Thread(new Producer(inMemoryQueue));
        Thread consumer1 = new Thread(new Consumer(inMemoryQueue));
        Thread consumer2 = new Thread(new Consumer(inMemoryQueue));

        producer1.start();
        consumer1.start();
        consumer2.start();
    }
}
