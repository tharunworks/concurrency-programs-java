package _05_multiple_callable_tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * We can run Runnable tasks using the Thread class or ExecutorService, whereas we can only run Callables using the latter.
 */
public class Runner {
    void init() {
        /**
         * A Future represents the result of an asynchronous computation.
         * Methods are provided to check if the computation is complete, to wait for its completion,
         * and to retrieve the result of the computation.
         * The result can only be retrieved using method get when the computation has completed, blocking if necessary until it is ready.
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int nTasks = 100;
        CallableTask[] callableTasks = new CallableTask[nTasks];
        Future<String>[] callableFutures = new Future[nTasks];
        for (int i = 0; i < nTasks; i++) {
            callableTasks[i] = new CallableTask();
            Future<String> future = executorService.submit(callableTasks[i]);
            callableFutures[i] = future;
        }
        for (int i = 0; i < nTasks; i++) {
            try {
                System.out.println("get on Callable Task: #" + i + ": " + callableFutures[i].get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
