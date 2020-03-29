import java.util.Scanner;

/**
 * Takes positive integers as input until the user enters -1 to quit.
 * Returns the following:
 * - The lowest value entered
 * - The largest value entered
 * - The number of values entered
 * - The sum of the values entered
 * - The mean of the values entered
 *
 * @author Will Goodman
 */
public class IntegerSequenceParser {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //lowest set to -1 by default so that if the input is the first to be entered, lowest can be set to it, to give an initial estimate.
        int lowest = -1;
        int highest = 0;
        int numOfValues = 0;
        int sum = 0;
        float mean = 0f;

        boolean endSequence = false;
        boolean validInput;
        // default input is to quit the program
        int input = -1;

        System.out.println("Please enter a positive integer (or -1 to stop):");

        // loop through asking the user for integers until they enter -1 to quit
        while (!endSequence) {
            validInput = false;

            while (!validInput) {
                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
                    if (input < -1) {
                        System.out.println("Input must be a positive integer (or -1 to stop):");
                    } else {
                        validInput = true;
                    }
                } else {
                    System.out.println("Input must be a positive integer (or -1 to stop):");
                    scanner.next();
                }
            }

            if (input == -1) {
                System.out.println("End of sequence.");
                endSequence = true;
            } else {

                if (lowest == -1 || input < lowest) {
                    lowest = input;
                }

                if (input > highest) {
                    highest = input;
                }

                numOfValues++;

                sum += input;
            }
        }

        scanner.close();

        mean = (float) sum / numOfValues;

        System.out.println("The lowest value entered is: " + lowest);
        System.out.println("The highest value entered is: " + highest);
        System.out.println("The number of values entered is: " + numOfValues);
        System.out.println("The sum of the values entered is: " + sum);
        System.out.println("The mean of the values entered is: " + mean);
    }

}
