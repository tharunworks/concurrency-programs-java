package _07_producer_consumer_without_blocking_queue;

public class Runner {

    MyBlockingQueue<Item> myBlockingQueue;
    void init(){
        myBlockingQueue = new MyBlockingQueue<>();
        Thread producerThread1 = new Thread(new Producer(myBlockingQueue));
        Thread producerThread2 = new Thread(new Producer(myBlockingQueue));
        Thread consumerThread1 = new Thread(new Consumer(myBlockingQueue));
        Thread consumerThread2 = new Thread(new Consumer(myBlockingQueue));
        consumerThread1.start();
        producerThread1.start();
        producerThread2.start();
        consumerThread2.start();

        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
