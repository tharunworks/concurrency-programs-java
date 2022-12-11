package _02_multiple_threads;

public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Task obj reference: " + this);
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
