import java.util.Scanner;

public class UC3_UserInput {

    public static void main(String[] args) {

        int slot = getUserSlot();
        System.out.println("Slot entered: " + slot);

    }

    // Method to take user input
    static int getUserSlot() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter slot number (1-9): ");
        int slot = sc.nextInt();   // reads integer input

        return slot; // return value to main
    }
}