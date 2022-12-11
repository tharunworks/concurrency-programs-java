package _01_single_thread;

public class Runner {
    void init(){
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();
    }
}
