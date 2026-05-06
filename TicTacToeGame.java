import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe Game
 * UC1 - Display Empty Board
 * UC2 - Toss to Decide First Player and Symbol
 * UC3 - Accept User Slot Input
 * UC4 - Convert Slot to Row & Column
 * UC5 - Validate User Move
 * UC6 - Place Move on Board
 * UC7 - Computer Makes Random Move
 * UC8 - Continuous Turn-Based Game Loop
 */
public class TicTacToeGame {

    // =========================
    // UC1: Board Representation
    // =========================
    static char[][] board = new char[3][3];

    // =========================
    // UC2: Game State Variables
    // =========================
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    // =========================
    // UC8: Game Loop Flags
    // =========================
    static boolean gameOver = false;

    // =========================
    // UC3: Global Scanner
    // =========================
    static Scanner sc = new Scanner(System.in);

    // Random object
    static Random random = new Random();

    public static void main(String[] args) {

        // =========================
        // UC1: Initialize Board
        // =========================
        initializeBoard();

        // =========================
        // UC2: Toss Logic
        // =========================
        tossAndAssignSymbols();
        displayTossResult();

        // =========================
        // UC8: Continuous Game Loop
        // =========================
        while (!gameOver) {

            // Print current board
            printBoard();

            // =========================
            // Human Turn
            // =========================
            if (isHumanTurn) {

                System.out.println("\n--- Human Turn ---");

                int slot = getUserSlot();

                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);

                // UC5: Validate move
                if (isValidMove(row, col)) {

                    // UC6: Place move
                    placeMove(row, col, humanSymbol);

                    // Switch turn
                    isHumanTurn = false;

                } else {

                    System.out.println("Invalid move! Try again.");
                }

            }

            // =========================
            // Computer Turn
            // =========================
            else {

                System.out.println("\n--- Computer Turn ---");

                // UC7: Computer random move
                computerMove();

                // Switch turn
                isHumanTurn = true;
            }

            // =========================
            // UC8: Temporary Stop Condition
            // (Until win/draw UC is added)
            // =========================
            if (isBoardFull()) {

                gameOver = true;

                System.out.println("\nBoard is full!");
                System.out.println("Game Over!");
            }
        }

        // Final board
        printBoard();
    }

    // =========================
    // UC1: Initialize Board
    // =========================
    static void initializeBoard() {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                board[row][col] = '-';
            }
        }
    }

    // =========================
    // UC1: Print Board
    // =========================
    static void printBoard() {

        System.out.println("\nCurrent Board:");
        System.out.println("-------------");

        for (int row = 0; row < 3; row++) {

            System.out.print("| ");

            for (int col = 0; col < 3; col++) {

                System.out.print(board[row][col] + " | ");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    // =========================
    // UC2: Toss Logic
    // =========================
    static void tossAndAssignSymbols() {

        int toss = random.nextInt(2);

        if (toss == 0) {

            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';

        } else {

            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    // =========================
    // UC2: Display Toss Result
    // =========================
    static void displayTossResult() {

        System.out.println("\n--- Toss Result ---");

        if (isHumanTurn) {

            System.out.println("You won the toss!");
            System.out.println("You play first.");

        } else {

            System.out.println("Computer won the toss!");
            System.out.println("Computer plays first.");
        }

        System.out.println("Your symbol: " + humanSymbol);
        System.out.println("Computer symbol: " + computerSymbol);
    }

    // =========================
    // UC3: Get User Input
    // =========================
    static int getUserSlot() {

        System.out.print("\nEnter slot number (1-9): ");

        return sc.nextInt();
    }

    // =========================
    // UC4: Convert Slot → Row
    // =========================
    static int getRowFromSlot(int slot) {

        return (slot - 1) / 3;
    }

    // =========================
    // UC4: Convert Slot → Column
    // =========================
    static int getColFromSlot(int slot) {

        return (slot - 1) % 3;
    }

    // =========================
    // UC5: Validate Move
    // =========================
    static boolean isValidMove(int row, int col) {

        // Boundary check
        if (row < 0 || row > 2 || col < 0 || col > 2) {

            return false;
        }

        // Empty cell check
        if (board[row][col] != '-') {

            return false;
        }

        return true;
    }

    // =========================
    // UC6: Place Move
    // =========================
    static void placeMove(int row, int col, char symbol) {

        board[row][col] = symbol;
    }

    // =========================
    // UC7: Computer Random Move
    // =========================
    static void computerMove() {

        int slot;
        int row;
        int col;

        while (true) {

            // Random slot 1-9
            slot = random.nextInt(9) + 1;

            // Convert slot
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);

            // Check validity
            if (isValidMove(row, col)) {

                // Place move
                placeMove(row, col, computerSymbol);

                System.out.println("Computer selected slot: " + slot);

                break;
            }
        }
    }

    // =========================
    // UC8: Check if Board Full
    // =========================
    static boolean isBoardFull() {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row][col] == '-') {

                    return false;
                }
            }
        }

        return true;
    }
}