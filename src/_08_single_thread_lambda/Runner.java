package _08_single_thread_lambda;
public class Runner {
    void init(){
//        we can use lambda expression only on functional interfaces(i.e., interface with only 1 method). In this case, it's Runnable interface
//        has only 1 method run().
//        lambda expression prevents creation of additional class.
        Runnable runnableTask = () ->{
            System.out.println("hello world");
        };
        Thread thread = new Thread(runnableTask);
        thread.start();
    }
}
