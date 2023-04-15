package _08_02_single_thread_lambda_function;


public class Task {
    public void print() {
        System.out.println("Thread Name: " + Thread.currentThread().getName());
    }

    public void sayHello() {
        System.out.println("Hello Thread Name: " + Thread.currentThread().getName());
    }
}
