package _20_track_runnable_tasks_status;

public class Runner {
    void init() {
        TaskStatusTracker taskStatusTracker = new TaskStatusTracker();
        String rTaskId1 = "r123";
        RunnableTask runnableTask1 = new RunnableTask(rTaskId1);
        String rTaskId2 = "r456";
        RunnableTask runnableTask2 = new RunnableTask(rTaskId2);
        String cTaskId1 = "c789";
        CallableTask callableTask1 = new CallableTask(cTaskId1);
        taskStatusTracker.scheduleTask(runnableTask1);
        taskStatusTracker.scheduleTask(runnableTask2);
        taskStatusTracker.scheduleTask(callableTask1);
        taskStatusTracker.getTaskStatus(rTaskId1);
        taskStatusTracker.getTaskStatus(rTaskId2);
        taskStatusTracker.getTaskStatus(cTaskId1);
        try {
            System.out.println("waiting for tasks to complete");
            Thread.sleep(4000); //wait for task to complete.
            System.out.println("all tasks completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        taskStatusTracker.getTaskStatus(rTaskId1);
        taskStatusTracker.getTaskStatus(rTaskId2);
        taskStatusTracker.getTaskStatus(cTaskId1);
        taskStatusTracker.shutDown();
    }
}
