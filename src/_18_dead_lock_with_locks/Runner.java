package _18_dead_lock_with_locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    void init(){
        ExecutorService executorService =  Executors.newFixedThreadPool(2);
        SharedResource sharedResource = new SharedResource();
        executorService.submit(() -> {
            try {
                sharedResource.method1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                sharedResource.method2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
