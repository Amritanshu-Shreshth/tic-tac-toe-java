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
 * UC9 - Check Winning Condition
 */
public class TicTacToeGame {

    // UC1: Board Representation
    static char[][] board = new char[3][3];

    // UC2: Game State Variables
    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    // UC8: Game Loop Flags
    static boolean gameOver = false;

    // UC3: Global Scanner
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {

        initializeBoard();
        tossAndAssignSymbols();
        displayTossResult();

        // UC8: Continuous Game Loop
        while (!gameOver) {

            printBoard();

            // =========================
            // Human Turn
            // =========================
            if (isHumanTurn) {
                System.out.println("\n--- Human Turn ---");
                int slot = getUserSlot();
                int row = getRowFromSlot(slot);
                int col = getColFromSlot(slot);

                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    
                    // UC9: Check if Human Won
                    if (hasWon(humanSymbol)) {
                        printBoard();
                        System.out.println("\nCongratulations! You won the game!");
                        gameOver = true;
                    } else if (isBoardFull()) {
                        printBoard();
                        System.out.println("\nIt's a Draw! Board is full.");
                        gameOver = true;
                    } else {
                        isHumanTurn = false; // Switch turn
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            } 
            // =========================
            // Computer Turn
            // =========================
            else {
                System.out.println("\n--- Computer Turn ---");
                computerMove();

                // UC9: Check if Computer Won
                if (hasWon(computerSymbol)) {
                    printBoard();
                    System.out.println("\nComputer wins! Better luck next time.");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("\nIt's a Draw! Board is full.");
                    gameOver = true;
                } else {
                    isHumanTurn = true; // Switch turn
                }
            }
        }
        System.out.println("Game Over!");
    }

    // UC1: Initialize Board
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    // UC1: Print Board
    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // UC2: Toss Logic
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

    // UC3 & UC4: Input and Conversion
    static int getUserSlot() {
        System.out.print("Enter slot number (1-9): ");
        return sc.nextInt();
    }

    static int getRowFromSlot(int slot) { return (slot - 1) / 3; }
    static int getColFromSlot(int slot) { return (slot - 1) % 3; }

    // UC5 & UC6: Validation and Placement
    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        return board[row][col] == '-';
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // UC7: Computer Random Move
    static void computerMove() {
        int slot, row, col;
        while (true) {
            slot = random.nextInt(9) + 1;
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);
            if (isValidMove(row, col)) {
                placeMove(row, col, computerSymbol);
                System.out.println("Computer selected slot: " + slot);
                break;
            }
        }
    }

    // UC8: Check Draw
    static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') return false;
            }
        }
        return true;
    }

    /**
     * UC9: Winning Condition Check
     * Checks all rows, columns, and diagonals for a win.
     */
    static boolean hasWon(char symbol) {
        // Check Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) 
                return true;
        }

        // Check Columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) 
                return true;
        }

        // Check Diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) 
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) 
            return true;

        return false;
    }
}