import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Checks a given password follows the below rules:
 * - Be at least 8 characters in length
 * - Contain at least one upper case letter
 * - Contain at least one digit
 * - Contain no spaces
 *
 * @author Will Goodman
 */
public class PasswordChecker {
    public static void main(String[] args) {

        ArrayList<String> errorMessages = new ArrayList<String>();

        if (args.length != 1) {
            System.out.println("This program only takes one input.");
        } else {
            String password = args[0];

            if (password.length() < 8) {
                errorMessages.add("- Password must be at least 8 characters long");
            }

            // check if there is any capital letters in the input
            if (!Pattern.matches(".*[A-Z]+.*", password)) {
                errorMessages.add("- Password must contain at least one upper-case character");
            }

            // check for one or more digits
            if (!Pattern.matches(".*[0-9]+.*", password)) {
                errorMessages.add("- Password must contain at least one digit");
            }


            // check for one or more spaces
            if (Pattern.matches(".*[ ]+.*", password)) {
                errorMessages.add("- Password must not contain any spaces");
            }

            // if any errors were found, tell the user what they were
            if (errorMessages.size() > 0) {
                System.out.println("Your password is not strong enough as it does not adhere to the following rules:");
                for (String message : errorMessages) {
                    System.out.println(message);
                }
            } else {
                System.out.println("Your password is strong enough.");
            }
        }
    }
}
