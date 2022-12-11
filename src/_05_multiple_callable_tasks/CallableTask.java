package _05_multiple_callable_tasks;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("In call method");
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        return "hello " + new Random().nextInt();
    }
}
