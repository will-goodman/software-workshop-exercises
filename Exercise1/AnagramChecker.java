import java.util.Scanner;

/**
 * Compares two strings and returns whether they are anagrams of each other
 *
 * @author Will Goodman
 */
public class AnagramChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your first string:");
        String firstString = scanner.nextLine();

        System.out.println("Please enter your second string:");
        String secondString = scanner.nextLine();

        scanner.close();

        if (areAnagrams(firstString, secondString)) {
            System.out.println("The two strings are anagrams.");
        } else {
            System.out.println("The two strings are not anagrams.");
        }
    }

    /**
     * Compares two strings and returns whether they are anagrams of each other
     *
     * @param firstString  The first string to compare
     * @param secondString The second string to compare
     * @return Whether the two strings are anagrams of each other
     */
    private static boolean areAnagrams(String firstString, String secondString) {

        // if the strings are different lengths then they cannot be anagrams
        if (firstString.length() != secondString.length()) {
            return false;
        } else {

            /**
             * iterate over each character in the first string, and remove the first instance of that character
             * in the second string (if present)
             *
             * if the first and second strings contain the exact same characters, then the second string will end up
             * empty
             * **/
            for (char character : firstString.toCharArray()) {
                secondString = secondString.replaceFirst(String.valueOf(character), "");
            }

            if (secondString.equals("")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
