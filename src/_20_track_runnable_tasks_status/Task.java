package _20_track_runnable_tasks_status;

public abstract class Task {
    private final String taskId;

    protected Task(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }
}
