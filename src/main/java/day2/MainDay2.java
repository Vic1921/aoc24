package day2;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainDay2 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/day2/input_day2.txt");
        Scanner scanner = new Scanner(file);

        int rowNumber = 0;
        boolean allRowsValid = true; // Flag to track validity

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            List<Integer> rowData = new ArrayList<>();

            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNextInt()) {
                rowData.add(lineScanner.nextInt());
            }
            lineScanner.close();

            // Validate the row
            if (!isRowValid(rowData)) {
                System.out.println("Invalid row at line " + rowNumber + ": " + rowData);
                allRowsValid = false;
                continue;
            }

            rowNumber++;
        }

        scanner.close();

        if (allRowsValid) {
            System.out.println("All rows are valid!");
        }
        else {
            System.out.println("Some rows are invalid! \n\n And exactly how many: " + rowNumber);
        }
    }

    public static boolean isRowValid(List<Integer> row) {
        if (row.isEmpty()) return true; // An empty row is valid

        // Helper function to check if a row is valid without modifications
        if (isValidWithoutModification(row)) return true;

        // Try removing one level and check if the remaining row becomes valid
        for (int i = 0; i < row.size(); i++) {
            List<Integer> modifiedRow = new ArrayList<>(row);
            modifiedRow.remove(i); // Simulate removing the problematic level
            if (isValidWithoutModification(modifiedRow)) return true;
        }

        return false; // The row cannot be made valid, even with one removal
    }

    // Helper method to check validity without modification
    private static boolean isValidWithoutModification(List<Integer> row) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < row.size(); i++) {
            int diff = row.get(i) - row.get(i - 1);

            // Check for increasing or decreasing order
            if (diff < 0) isIncreasing = false;
            if (diff > 0) isDecreasing = false;

            // Check if adjacent integers differ by at least 1 and at most 3
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false; // Invalid if the difference is outside the range [1, 3]
            }
        }

        return isIncreasing || isDecreasing; // Must be strictly increasing or decreasing
    }

}
