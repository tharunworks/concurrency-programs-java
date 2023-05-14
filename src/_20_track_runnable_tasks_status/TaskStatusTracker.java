package _20_track_runnable_tasks_status;

import java.util.concurrent.*;

public class TaskStatusTracker {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final ConcurrentHashMap<String, Future<?>> taskStatus = new ConcurrentHashMap<>();

    protected void scheduleTask(Task task) {
        if (taskStatus.get(task.getTaskId()) != null) {
            System.out.println("taskId: " + task.getTaskId() + " already exists");
            return;
        }
        if (task instanceof CallableTask) {
            Future<?> future = executorService.submit((Callable) task);
            taskStatus.put(task.getTaskId(), future);
        } else {
            Future<?> future = executorService.submit((Runnable) task);
            taskStatus.put(task.getTaskId(), future);
        }
    }

    protected void getTaskStatus(String taskId) {
        if (taskStatus.get(taskId) == null) {
            System.out.println("Status of TaskId: " + taskId + " doesn't exist");
        } else if (taskStatus.get(taskId) != null && taskStatus.get(taskId).isDone()) {
            System.out.println("Status of TaskId: " + taskId + " completed");
            try {
                // for runnable task below value is null, for callable task it'll be non-null as isDone() is true.
                System.out.println("Value of TaskId: " + taskId + " " + taskStatus.get(taskId).get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else if (taskStatus.get(taskId) != null) {
            System.out.println("Status of TaskId: " + taskId + " still in progress");
        }
    }

    protected void shutDown() {
        executorService.shutdown();
    }

}
