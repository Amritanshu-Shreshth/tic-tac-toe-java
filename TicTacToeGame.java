import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe Game
 * UC1 - Display Empty Board
 * UC2 - Toss to Decide First Player and Symbol
 * UC3 - Accept User Slot Input
 * UC4 - Convert Slot to Row & Column
 * UC5 - Validate User Move
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
    // UC3: Scanner (global)
    // =========================
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // UC1
        initializeBoard();
        printBoard();

        // UC2
        tossAndAssignSymbols();
        displayTossResult();

        // UC3
        int slot = getUserSlot();

        // UC4
        int row = getRowFromSlot(slot);
        int col = getColFromSlot(slot);

        // UC5
        boolean isValid = isValidMove(row, col);

        System.out.println("\nConverted Position:");
        System.out.println("Row: " + row);
        System.out.println("Column: " + col);
        System.out.println("Is move valid? " + isValid);
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
        Random random = new Random();
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
            System.out.println("You won the toss! You play first.");
        } else {
            System.out.println("Computer won the toss! Computer plays first.");
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

        // Check boundaries (0–2)
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

        // Check if cell is empty
        if (board[row][col] != '-') {
            return false;
        }

        return true;
    }
}