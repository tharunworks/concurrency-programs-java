package _18_dead_lock_with_locks;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    void method1() throws InterruptedException {

        System.out.println("M1:waiting for lock1");
        lock1.lock();
        System.out.println("M1:fetched lock1");
        Thread.sleep(1000);
        System.out.println("M1:waiting for lock2");
        lock2.lock();
        System.out.println("M1:fetched lock2");

        System.out.println("M1:In method1");

        lock2.unlock();
        lock1.unlock();
    }

    void method2() throws InterruptedException {

        System.out.println("M2:waiting for lock2");
        lock2.lock();
        System.out.println("M2:fetched lock2");
        Thread.sleep(1000);
        System.out.println("M2:waiting for lock1");
        lock1.lock();
        System.out.println("M2:fetched lock1");

        System.out.println("M2:In method2");

        lock2.unlock();
        lock1.unlock();

    }
}

