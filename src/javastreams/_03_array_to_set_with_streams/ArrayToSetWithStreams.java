package javastreams._03_array_to_set_with_streams;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ArrayToSetWithStreams {
    public static void main(String[] args) {
        // Define an array with elements
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5};

        // Convert the array to a set using Java Streams
        Set<Integer> numberSet = Arrays.stream(numbers)
                .collect(Collectors.toSet());

        // Print the set
        System.out.println("Set: " + numberSet);
    }
}

