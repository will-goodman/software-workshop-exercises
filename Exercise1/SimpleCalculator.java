import java.util.Scanner;
import java.util.Arrays;

/**
 * Performs basic arithmetic on positive integers
 *
 * @author Will Goodman
 */
public class SimpleCalculator {

    private final static String[] VALID_OPERATORS = {"+", "-", "*", "/", "%"};

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        String input = "";
        String firstOperand = "";
        String operator = "";
        String secondOperand = "";

        System.out.println("Please enter a mathematical expression in the form <operand> <operator> <operand>:");

        //keep asking the user for an input until a valid one is entered
        while (!validInput) {

            input = scanner.nextLine();

            //store each word in the input as items in an array
            String[] inputparts = input.split(" ");

            //if there are not three words in the array, then the input cannot contain two operands and an operator
            if (inputparts.length != 3) {
                System.out.println("There must be at least three parts to the input (<operand> <operator> <operand>), please try again:");
            } else {

                firstOperand = inputparts[0];
                operator = inputparts[1];
                secondOperand = inputparts[2];

                if (!isPositiveInteger(firstOperand) || !isPositiveInteger(secondOperand)) {
                    System.out.println("Operands must be positive integers, please try again:");
                } else {
                    //if the operator is one of the valid options, then we can continue
                    if (!Arrays.asList(VALID_OPERATORS).contains(operator)) {
                        System.out.println("Operator must be one of the following: +, -, *, /, %");
                    } else {

                        if ((operator.equals("/") || operator.equals("%")) && secondOperand.equals("0")) {
                            System.out.println("Cannot perform division or modulus by zero");
                        } else {
                            validInput = true;
                        }
                    }
                }
            }
        }

        scanner.close();

        int firstInteger = Integer.parseInt(firstOperand);
        int secondInteger = Integer.parseInt(secondOperand);

        //variable to store the result of the operation. It is an integer as we are just using integer division here
        int result = 0;

        switch (operator) {
            case "+":
                result = firstInteger + secondInteger;
                break;
            case "-":
                result = firstInteger - secondInteger;
                break;
            case "*":
                result = firstInteger * secondInteger;
                break;
            case "/":
                result = firstInteger / secondInteger;
                break;
            case "%":
                result = firstInteger % secondInteger;
                break;
        }

        System.out.println("The result is: " + result);
    }


    /**
     * Sees if a String is a positive integer
     *
     * @param stringValue The string to check
     * @return Whether the String is a positive integer
     */
    private static boolean isPositiveInteger(String stringValue) {

        try {
            int integerValue = Integer.parseInt(stringValue);
            if (integerValue < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }

}
