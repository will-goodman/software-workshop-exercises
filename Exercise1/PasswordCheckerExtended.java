import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Month;
import java.time.format.TextStyle;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Takes a username, date of birth, and password as input.
 * Checks the password follows the below rules:
 * - Be at least 8 characters in length
 * - Contain at least one upper case letter
 * - Contain at least one digit
 * - Contain no spaces
 * - Not contain the username, or versions of the username
 * - Not contain any of the information from the D.O.B
 *
 * @author Will Goodman
 */
public class PasswordCheckerExtended {

    public static void main(String[] args) {

        ArrayList<String> errorMessages = new ArrayList<String>();

        if (args.length != 3) {
            printUsageInformation();
        } else {
            String username = args[0];
            String dateOfBirth = args[1];
            String password = args[2];

            if (!Pattern.matches("^\\d{2}\\/\\d{2}\\/\\d{4}$", dateOfBirth)) {
                printUsageInformation();
            } else {
                try {

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

                    // check username is not present in password
                    Pattern usernameRegex = Pattern.compile(String.format(".*%s.*", username), Pattern.CASE_INSENSITIVE);
                    Matcher usernameMatcher = usernameRegex.matcher(password);
                    if (usernameMatcher.find()) {
                        errorMessages.add("- Username must not be contained within the password");
                    }

                    // check none of the details from the D.O.B are in the password
                    SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
                    dateParser.setLenient(false);
                    Date parsedDateOfBirth = dateParser.parse(dateOfBirth);
                    LocalDate localDateOfBirth = parsedDateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String day = Integer.toString(localDateOfBirth.getDayOfMonth());
                    Month month = localDateOfBirth.getMonth();
                    String year = Integer.toString(localDateOfBirth.getYear());

                    if (password.contains(day) || password.contains(year) || password.contains(Integer.toString(month.getValue()))) {
                        errorMessages.add("- Password cannot contain details from D.O.B");
                    } else {
                        String[] monthDisplayNames = {month.getDisplayName(TextStyle.FULL, Locale.UK), month.getDisplayName(TextStyle.SHORT, Locale.UK)};
                        Pattern monthRegex;

                        for (String displayName : monthDisplayNames) {
                            monthRegex = Pattern.compile(String.format(".*%s.*", displayName), Pattern.CASE_INSENSITIVE);
                            Matcher monthMatcher = monthRegex.matcher(password);
                            if (monthMatcher.find()) {
                                errorMessages.add("- Password cannot contain details from D.O.B");
                                break;
                            }
                        }
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

                } catch (ParseException e) {
                    printUsageInformation();
                }
            }
        }
    }

    /**
     * Prints information on how to run the program.
     */
    private static void printUsageInformation() {
        System.out.println("You must enter three items in speech marks: your username, D.O.B. and password.");
        System.out.println("They must adhere to the following rules:");
        System.out.println("- D.O.B. must be in the format dd/mm/yyyy");
        System.out.println("- The password must not contain any information from the username or D.O.B.");
        System.out.println("- The password must be 8 characters or longer");
        System.out.println("- It must contain at least one capital letter");
        System.out.println("- It must contain at least one digit");
        System.out.println("- It must not contain any spaces");
    }
}
