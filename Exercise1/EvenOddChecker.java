import java.util.Scanner;

/**
 * Returns whether a given positive integer is even or odd
 *
 * @author Will Goodman
 */
public class EvenOddChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int input = 0;
        boolean isPositiveInt = false;

        System.out.println("Please enter a positive integer:");

        // loop until the user inputs a positive integer
        while (!isPositiveInt) {

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1) {
                    isPositiveInt = true;
                } else {
                    System.out.println("Input must be 1 or greater, please try again:");
                }
            } else {
                System.out.println("Input must be an integer, please try again:");
                scanner.next();
            }
        }

        scanner.close();

        // divide the integer by two, if the modulus is zero it must be even
        if (input % 2 == 0) {
            System.out.println("The number is even.");
        } else {
            System.out.println("The number is odd.");
        }

    }
}
