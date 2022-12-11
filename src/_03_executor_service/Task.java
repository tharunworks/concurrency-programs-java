package _03_executor_service;

public class Task implements Runnable{
    @Override
    public void run() {
//        System.out.println("Task obj reference: " + this);
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
