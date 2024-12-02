package noorg.vic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("src/main/java/noorg/vic/day1/input.txt");
            Scanner scanner = new Scanner(file);

            // Read values into an array
            int[] values = new int[2000]; // Replace 100 with the actual number of values
            int index = 0;
            while (scanner.hasNextInt()) {
                values[index++] = scanner.nextInt();
            }
            scanner.close();

            // Split the 2 arrays
            // first array takes all the even values
            // second array takes in all the odd values

            int[] firstHalf = new int[values.length / 2];
            int[] secondHalf = new int[values.length / 2];
            int firstHalfIndex = 0;
            int secondHalfIndex = 0;
            for (int i = 0; i < values.length; i++) {
                if (i % 2 == 0) {
                    firstHalf[firstHalfIndex++] = values[i];
                } else {
                    secondHalf[secondHalfIndex++] = values[i];
                }
            }


            // Sort the array
            Arrays.sort(firstHalf);
            Arrays.sort(secondHalf);

            // Print for test both
            System.out.println("This is the first half: " + Arrays.toString(firstHalf));
            System.out.println("This is the second half: " + Arrays.toString(secondHalf));

            // Sum the difference between the 2 arrays
            int sum = 0;
            for (int i = 0; i < firstHalf.length; i++) {
                sum += Math.abs(firstHalf[i] - secondHalf[i]);
            }
            System.out.println("The sum is: " + sum);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}