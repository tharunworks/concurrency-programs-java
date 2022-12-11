package _06_producer_consumer_blocking_queue;

import java.util.concurrent.*;

public class Runner {

    BlockingQueue<Item> itemBlockingQueue = new ArrayBlockingQueue<>(5);
    void init(){

        Thread producerThread1 = new Thread(new Producer(itemBlockingQueue));
        Thread producerThread2 = new Thread(new Producer(itemBlockingQueue));
        Thread consumerThread1 = new Thread(new Consumer(itemBlockingQueue));
        Thread consumerThread2 = new Thread(new Consumer(itemBlockingQueue));
        consumerThread1.start();
        producerThread1.start();
        producerThread2.start();
        consumerThread2.start();

        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
