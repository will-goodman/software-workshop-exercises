import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Takes a series of strings, and then outputs the following information:
 * - strings in length ascending order
 * - strings in length descending order
 * - the longest string
 *
 * @author Will Goodman
 */
public class StringLengthSorter {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> strings = new ArrayList<String>();

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

        if (strings.size() > 0) {
            Collections.sort(strings, new Comparator<String>() {
                @Override
                public int compare(String firstString, String secondString) {
                    if (firstString != null && secondString != null) {

                        int firstLength = firstString.length();
                        int secondLength = secondString.length();
                        if (firstLength < secondLength) {
                            return -1;
                        } else if (firstLength == secondLength) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else if (firstString == null) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            System.out.format("Ascending order: %s\n", strings);

            Collections.reverse(strings);
            System.out.format("Descending order: %s\n", strings);

            System.out.format("The longest string is: %s\n", strings.get(0));
        }
    }
}
