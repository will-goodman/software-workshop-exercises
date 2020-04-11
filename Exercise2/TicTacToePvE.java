import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Allows a player to play a game of Tic-Tac-Toe against an AI.
 *
 * The AI makes decisions by using the minimax algorithm.
 *
 * @author Will Goodman
 */
public class TicTacToePvE {

    private final static Character EMPTY_SQUARE = '-';
    private final static int WIN = 1;
    private final static int DRAW = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Character[][] gameBoard = new Character[][]{
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE},
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE},
                {EMPTY_SQUARE, EMPTY_SQUARE, EMPTY_SQUARE}
        };

        String playerInput = "";
        while (!(playerInput.equals("x") || playerInput.equals("o"))) {
            System.out.println("Please enter your character (x or o):");
            playerInput = scanner.nextLine();
        }
        Character player = playerInput.charAt(0);

        Character computer = 'x';
        switch (player) {
            case 'x':
                computer = 'o';
                break;
            case 'o':
                computer = 'x';
                break;
        }


        for (int spacesFilled = 0; spacesFilled < 9; spacesFilled++) {
            // player plays on even iterations, computer on odd iterations
            if (spacesFilled % 2 == 0) {
                String move;
                // x and y will always be set in the while loop, but Java requires them to be initialised
                int x = -1;
                int y = -1;
                boolean isValidInput = false;
                while (!isValidInput) {
                    System.out.println("Please enter your next move:");
                    move = scanner.nextLine();

                    if (Pattern.matches("^[0-2]{1}[0-2]{1}$", move)) {
                        x = Character.getNumericValue(move.charAt(0));
                        y = Character.getNumericValue(move.charAt(1));

                        if (gameBoard[x][y] == '-') {
                            isValidInput = true;
                        }
                    }
                }

                gameBoard[x][y] = player;
            } else {
                String move = getNextBestMove(gameBoard, computer, player);

                int x = Character.getNumericValue(move.charAt(0));
                int y = Character.getNumericValue(move.charAt(1));

                gameBoard[x][y] = computer;
            }


            print2DArray(gameBoard);
        }

        boolean playerWon = hasWon(gameBoard, player);
        boolean computerWon = hasWon(gameBoard, computer);

        if (playerWon && computerWon) {
            System.out.println("Draw!");
        } else if (playerWon) {
            System.out.println("You won!");
        } else if (computerWon) {
            System.out.println("Computer won!");
        } else {
            System.out.println("Neither player won.");
        }

    }

    /**
     * Decides the next best move a player
     *
     * @param gameBoard Character[][] The Tic-Tac-Toe board
     * @param player Character The player to decide the next move for
     * @return The next move to play
     */
    private static String getNextBestMove(Character[][] gameBoard, Character player, Character opponent) {

        int highestScore = -2;
        String move = "";
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameBoard[x][y] == '-') {
                    gameBoard[x][y] = player;

                    int moveScore = -miniMax(gameBoard, opponent, player);
                    if (moveScore > highestScore) {
                        highestScore = moveScore;
                        move = String.format("%s%s", x, y);
                    }

                    gameBoard[x][y] = '-';
                }
            }
        }


        return move;
    }

    /**
     * Performs the minimax algorithm, working out a player's predicted score at the end of the game based on the current game state.
     *
     * @param gameBoard Character[][] The current gameBoard state
     * @param player Character The player who's score to predict
     * @param opponent Character The opponent of the player
     * @return -1 if the player will lose, 0 if it will be a draw, and 1 if the player will win.
     * */
    private static int miniMax(Character[][] gameBoard, Character player, Character opponent) {

        if (hasWon(gameBoard, player)) {
            return WIN;
        }

        int highestScore = -2;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameBoard[x][y] == '-') {
                    gameBoard[x][y] = player;

                    int moveScore = -miniMax(gameBoard, opponent, player);
                    if (moveScore > highestScore) {
                        highestScore = moveScore;
                    }

                    gameBoard[x][y] = '-';
                }
            }
        }

        if (highestScore == -2) {
            return DRAW;
        }

        return highestScore;
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