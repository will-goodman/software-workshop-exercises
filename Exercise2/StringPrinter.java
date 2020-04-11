import java.util.Scanner;
import java.util.LinkedList;

/**
 * Takes a series of strings, and outputs the number of strings entered, followed by each string on a new line
 *
 * @author Will Goodman
 */
public class StringPrinter {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LinkedList<String> strings = new LinkedList<String>();

        System.out.println("Please enter your strings, pressing enter after each one (enter 'quit' to stop)");

        boolean quit = false;
        String input;

        while (!quit) {
            input = scanner.nextLine();

            if (input.equals("quit")) {
                quit = true;
            } else {
                // don't accept empty strings
                if (input.length() != 0) {
                    strings.add(input);
                }
            }
        }

        System.out.println("Number of strings entered: " + strings.size());
        while (strings.size() != 0) {
            System.out.println("- " + strings.pop());
        }

    }
}
