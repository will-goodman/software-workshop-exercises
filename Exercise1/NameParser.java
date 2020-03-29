import java.util.Scanner;

/**
 * Prints either two or three word names out on separate lines
 *
 * @author Will Goodman
 */
public class NameParser {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean isValidName = false;
        String input;
        String[] nameWords = new String[0];

        System.out.println("Please enter your name:");

        // keep asking for an input until a valid name is entered
        while (!isValidName) {

            input = scanner.nextLine();

            // store each word in the input as a separate item in an array
            nameWords = input.split(" ");

            // if the array doesn't contain either two or three words, then the input is not valid
            if (nameWords.length == 2 || nameWords.length == 3) {
                isValidName = true;
            } else {
                System.out.println("Name must contain either two or three parts, please try again:");
            }
        }

        scanner.close();

        // output each word from the name on a separate line
        for (String word : nameWords) {
            System.out.println(word);
        }
    }

}
