import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Allows two players to play a game of Tic-Tac-Toe
 *
 * @author Will Goodman
 */
public class TicTacToe {

    private final static Character EMPTY_SQUARE = '-';

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Character[][] gameBoard = new Character[][]{
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE},
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE},
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE}
        };

        String playerOneInput = "";
        while (!(playerOneInput.equals("x") || playerOneInput.equals("o"))) {
            System.out.println("Player One, please enter your character (x or o):");
            playerOneInput = scanner.nextLine();
        }
        Character playerOne = playerOneInput.charAt(0);

        String playerTwoInput = "";
        while (!(playerTwoInput.equals("x") || playerTwoInput.equals("o")) || playerTwoInput.equals(playerOneInput)) {
            System.out.println("Player Two, please enter your character (x or o):");
            playerTwoInput = scanner.nextLine();
        }
        Character playerTwo = playerTwoInput.charAt(0);

        Character currentPlayer;
        String currentPlayerName;
        for (int spacesFilled = 0; spacesFilled < 9; spacesFilled++) {
            // player one plays on even iterations, player two on odd iterations
            if (spacesFilled % 2 == 0) {
                currentPlayer = playerOne;
                currentPlayerName = "One";
            } else {
                currentPlayer = playerTwo;
                currentPlayerName = "Two";
            }

            String move;
            // x and y will always be set in the while loop, but Java requires them to be initialised
            int x = -1;
            int y = -1;
            boolean isValidInput = false;
            while (!isValidInput) {
                System.out.println(String.format("Player %s, please enter your next move:", currentPlayerName));
                move = scanner.nextLine();

                if (Pattern.matches("^[0-2]{1}[0-2]{1}$", move)) {
                    x = Character.getNumericValue(move.charAt(0));
                    y = Character.getNumericValue(move.charAt(1));

                    if (gameBoard[x][y] == EMPTY_SQUARE) {
                        isValidInput = true;
                    }
                }
            }

            gameBoard[x][y] = currentPlayer;
            print2DArray(gameBoard);
        }

        boolean playerOneWon = hasWon(gameBoard, playerOne);
        boolean playerTwoWon = hasWon(gameBoard, playerTwo);

        if (playerOneWon && playerTwoWon) {
            System.out.println("Draw!");
        } else if (playerOneWon) {
            System.out.println("Player One won!");
        } else if (playerTwoWon) {
            System.out.println("Player Two won!");
        } else {
            System.out.println("Neither player won.");
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
