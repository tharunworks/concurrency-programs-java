package _04_simple_callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * We can run Runnable tasks using the Thread class or ExecutorService, whereas we can only run Callables using the latter.
 */
public class Runner {
    void init(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new CallableTask());
        try {
            /**
             * the return value from future i.e., get() method is blocking in nature.
             */
            String returnValue = future.get();
            System.out.println("Return value from callable task is " + returnValue);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
