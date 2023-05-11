package _13_movie_theater;

import java.util.concurrent.locks.ReentrantLock;

public class MovieTheater {
    private ReentrantLock[][] seatLocks;
    private SeatStatus[][] seats;

    public MovieTheater(int rows, int columns) {
        seats = new SeatStatus[rows][columns];
        seatLocks = new ReentrantLock[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j] = SeatStatus.AVAILABLE;
                seatLocks[i][j] = new ReentrantLock();
            }
        }
    }

    public boolean bookSeat(int row, int column) {
        seatLocks[row][column].lock();
        try {
            if (seats[row][column] == SeatStatus.AVAILABLE) {
                seats[row][column] = SeatStatus.BOOKED;
                System.out.println("Booked seat at row " + row + ", column " + column + ".");
                return true;
            } else {
                System.out.println("Sorry, seat at row " + row + ", column " + column + " is already booked.");
                return false;
            }
        } finally {
            seatLocks[row][column].unlock();
        }
    }

    public void viewSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seatLocks[i][j].lock();
                try {
                    if (seats[i][j] == SeatStatus.AVAILABLE) {
                        System.out.print("O ");
                    } else {
                        System.out.print("X ");
                    }
                } finally {
                    seatLocks[i][j].unlock();
                }
            }
            System.out.println();
        }
    }
}

