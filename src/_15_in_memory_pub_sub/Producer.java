package _15_in_memory_pub_sub;

import java.util.UUID;

public class Producer implements Runnable{

    InMemoryPubSubImpl2 inMemoryQueue;

    public Producer(InMemoryPubSubImpl2 inMemoryQueue) {
        this.inMemoryQueue = inMemoryQueue;
    }

    @Override
    public void run() {
//        while (true){
//            inMemoryQueue.put(String.valueOf(UUID.randomUUID()));
//        }
//        inMemoryQueue.put("hello");
//        inMemoryQueue.put("world");
        for(int i = 0; i < 10; i++){
            String message = String.valueOf(UUID.randomUUID());
            inMemoryQueue.put(i + ": " + message);
        }
    }
}
