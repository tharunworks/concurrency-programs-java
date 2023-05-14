package _20_track_runnable_tasks_status;

public class RunnableTask extends Task implements Runnable {

    public RunnableTask(String taskId) {
        super(taskId);
    }

    @Override
    public void run() {
        try {
            System.out.println("taskId: " + this.getTaskId() + " is running");
            Thread.sleep(2000);
            System.out.println("taskId: " + this.getTaskId() + " ran");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
