import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Takes input in the form 'xy' where x is the row number and y is the column number.
 * Sets the corresponding coordinate in a 2D array to 0.
 * Continues until the user enters 'quit'.
 *
 * @author Will Goodman
 */
public class ArrayCoordinateParser {

    public static void main(String[] args) {

        Integer[][] numbers = {
                {0, 1, 4, 5},
                {3, 7, 9, 7},
                {1, 8, 2, 1}
        };

        Scanner scanner = new Scanner(System.in);

        print2DArray(numbers);

        boolean quit = false;
        String input;
        while (!quit) {
            System.out.println("Please enter two single integer coordinates in the form 'xy' (or 'quit'):");

            input = scanner.nextLine();
            if (input.equals("quit")) {
                quit = true;
            } else if (Pattern.matches("^[0-2]{1}[0-3]{1}$", input)) {

                int x = Character.getNumericValue(input.charAt(0));
                int y = Character.getNumericValue(input.charAt(1));

                numbers[x][y] = 0;

                print2DArray(numbers);

            }

        }
    }

    /**
     * Prints a 2D array with no spaces and each row on a new line.
     * <p>
     * E.g. {{0,1},{2,3}}:
     * 01
     * 23
     *
     * @param array T[][] 2D array of values
     */
    private static <T> void print2DArray(T[][] array) {
        for (T[] row : array) {
            for (T value : row) {
                System.out.print(value);
            }
            System.out.print(System.lineSeparator());
        }
    }

}
