package _02_multiple_threads;

public class Runner {
    void init(){
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(new Task());
            thread.start();
        }
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
