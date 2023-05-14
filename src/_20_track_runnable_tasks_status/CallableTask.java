package _20_track_runnable_tasks_status;

import java.util.concurrent.Callable;

public class CallableTask extends Task implements Callable<String> {

    public CallableTask(String taskId) {
        super(taskId);
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println("taskId: " + this.getTaskId() + " is running");
            Thread.sleep(2000);
            System.out.println("taskId: " + this.getTaskId() + " ran");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }
}
