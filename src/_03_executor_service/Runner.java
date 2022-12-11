package _03_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The executor service maintains fixed set of threads rather creating new thread when necessary. This is useful
 * since creating a new thread adds cost like memory footprint.
 * note: compare with _02_multiple_threads package
 */
public class Runner {
    void init(){

//        create the pool with size 10.
        ExecutorService executorService = Executors.newFixedThreadPool(10);

//        submit the tasks of size 500 for execution
        for(int i = 0; i < 500; i++){
            executorService.submit(new Task());
        }

        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }
}
