package _13_movie_theater;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MovieTheater theater = new MovieTheater(5, 5);

        // Create an ExecutorService with a fixed thread pool
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Submit tasks to book seats concurrently
        for (int i = 0; i < 50; i++) {
            int row = (int) (Math.random() * 5);
            int column = (int) (Math.random() * 5);
            executor.submit(() -> theater.bookSeat(row, column));

            /*
            The view won't be proper since locks are at granular level of seats. To get proper view, lock has to be at all seats;
            executor.submit(theater::viewSeats);
             */

        }

        // Shut down the ExecutorService
        executor.shutdown();

        // Wait for all tasks to complete
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the seat availability
        theater.viewSeats();
    }
}

