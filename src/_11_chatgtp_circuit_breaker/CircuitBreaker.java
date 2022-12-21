package _11_chatgtp_circuit_breaker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreaker {
    private final int threshold;
    private final long timeout;
    private final AtomicInteger failureCount;
    private volatile long lastFailureTime;

    public CircuitBreaker(int threshold, long timeout) {
        this.threshold = threshold;
        this.timeout = timeout;
        this.failureCount = new AtomicInteger(0);
        this.lastFailureTime = 0;
    }

    public boolean isClosed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFailureTime > timeout) {
            failureCount.set(0);
        }
        return failureCount.get() < threshold;
    }

    public void recordFailure() {
        failureCount.incrementAndGet();
        lastFailureTime = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = new CircuitBreaker(3, 10000);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        while (true) {
            if (circuitBreaker.isClosed()) {
                executor.submit(() -> {
                    try {
                        // Make a request
                        System.out.println("Making request...");
                        System.out.println("Failing request...");
                        throw new Exception("failing request");
                    } catch (Exception e) {
                        // Record the failure
                        circuitBreaker.recordFailure();
                    }
                });
            } else {
                System.out.println("Circuit is open, cannot make request.");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
