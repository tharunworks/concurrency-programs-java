package javastreams._02_primitive_array_to_set_with_streams;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class PrimitiveArrayToSetWithStreams {
    public static void main(String[] args) {
        // Define a primitive int array with elements
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5};

        // Convert the primitive int array to a set using Java Streams
        Set<Integer> numberSet = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toSet());

        // Print the set
        System.out.println("Set: " + numberSet);
    }
}
