import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Decides which player has one on a given Tic-Tac-Toe game board
 *
 * @author Will Goodman
 */
public class TicTacToeChecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Character[][] gameBoard = new Character[3][3];

        boolean validInput = true;
        for (int row = 0; row < 3; row++) {
            System.out.println(String.format("Please enter row %d:", row + 1));

            String input = scanner.nextLine();

            if (Pattern.matches("^[x,o]{3}$", input)) {
                gameBoard[row] = new Character[]{input.charAt(0), input.charAt(1), input.charAt(2)};
            } else {
                validInput = false;
                break;
            }
        }

        if (!validInput) {
            System.out.println("Each row must consist of three letters which are either 'x' or 'o' e.g. xox");
        } else {

            print2DArray(gameBoard);

            boolean xWon = hasWon(gameBoard, 'x');
            boolean oWon = hasWon(gameBoard, 'o');

            if (xWon && oWon) {
                System.out.println("Draw!");
            } else if (xWon) {
                System.out.println("Player 'x' won!");
            } else if (oWon) {
                System.out.println("Player 'o' won!");
            } else {
                System.out.println("Neither player won.");
            }
        }
    }

    /**
     * Checks if a given player has won on a given game board
     *
     * @param gameBoard Character[][] The Tic-Tac-Toe board
     * @param player    Character The player to check
     */
    private static boolean hasWon(Character[][] gameBoard, Character player) {
        return (hasWonRow(gameBoard, player) || hasWonColumn(gameBoard, player) || hasWonDiagonal(gameBoard, player));
    }

    /**
     * Checks if a given player has won a horizontal row
     *
     * @param gameBoard Character[][] The Tic-Tac-Toe board
     * @param player    Character The player to check
     */
    private static boolean hasWonRow(Character[][] gameBoard, Character player) {

        Character[] winningRow = new Character[]{player, player, player};
        for (Character[] row : gameBoard) {
            if (Arrays.equals(row, winningRow)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a given player has won a vertical column
     *
     * @param gameBoard Character[][] The Tic-Tac-Toe board
     * @param player    Character The player to check
     */
    private static boolean hasWonColumn(Character[][] gameBoard, Character player) {
        Character[] firstColumn = new Character[3];
        Character[] secondColumn = new Character[3];
        Character[] thirdColumn = new Character[3];
        Character[] winningColumn = new Character[]{player, player, player};

        for (int y = 0; y < 3; y++) {
            firstColumn[y] = gameBoard[y][0];
            secondColumn[y] = gameBoard[y][1];
            thirdColumn[y] = gameBoard[y][2];
        }

        return (Arrays.equals(firstColumn, winningColumn) || Arrays.equals(secondColumn, winningColumn) || Arrays.equals(thirdColumn, winningColumn));
    }

    /**
     * Checks if a given player has won a diagonal path
     *
     * @param gameBoard Character[][] The Tic-Tac-Toe board
     * @param player    Character The player to check
     */
    private static boolean hasWonDiagonal(Character[][] gameBoard, Character player) {
        Character[] topLeftBottomRight = new Character[]{gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]};
        Character[] bottomLeftTopRight = new Character[]{gameBoard[2][0], gameBoard[1][1], gameBoard[0][2]};
        Character[] winningDiagonal = new Character[]{player, player, player};

        return (Arrays.equals(topLeftBottomRight, winningDiagonal) || Arrays.equals(bottomLeftTopRight, winningDiagonal));
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
