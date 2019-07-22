import java.awt.font.GlyphMetrics;
import java.util.Scanner;

public class Menu extends Games {

    public static void displayMenu() {
        System.out.println("********* CHOOSE GAME *********");
        System.out.println("1. Play Higher or Lower");
        System.out.println("2. Display Rules");
        System.out.println("0. Exit");
    }

    public static void menuChoice() {
        Scanner sc = new Scanner(System.in);
        boolean exitGame = false;

        do {
            displayMenu();
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    highOrLow();
                    break;
                case 2:
                    System.out.println("\nRULES:\nTry to guess if the next card is higher or");
                    System.out.println("lower than the last card. Guess by typing ");
                    System.out.println("'h' for Higher or 'l' for Lower. If the two");
                    System.out.println("cards are equal this counts as correct guess.");
                    System.out.println("\nCorrect guesses awards 1p.\n");
                    break;
                case 0:
                    exitGame = true;
                    break;
                default:
                    System.out.println("Wrong choice, try again!");
                    break;

            }
        } while (!exitGame);
    }
}

