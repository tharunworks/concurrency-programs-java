package _01_single_thread;

/**
 * This task/class is called Runnable-Task since it implements Runnable interface.
 * If it implements Callable then it's called Callable-Task.
 */
public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
