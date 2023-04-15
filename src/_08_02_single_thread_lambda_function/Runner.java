package _08_02_single_thread_lambda_function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    void init(){
        Task task = new Task();
//        Runnable runnableTask = () ->{
//            task.print();
//        };
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            executor.submit(() -> task.print());
            executor.submit(() -> task.sayHello());
        }
    }
}
