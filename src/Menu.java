import java.util.Scanner;

public class Menu extends PlayingCardGame {

    public static void displayMenu() {

        System.out.println("********* CHOOSE GAME *********");
        System.out.println("1. Higher or Lower");
        System.out.println("2. Hugo");
        System.out.println("0. Exit");
    }

    public static void menuChoice() {
        Scanner sc = new Scanner(System.in);
        boolean exitGame = false;

        while (true) {
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    highOrLow();
                    break;
                case 2:
                    hugo();
                    break;
                case 0:
                    exitGame = true;
                    return;
                default:
                    System.out.println("Wrong choice, try again!");
                    break;

            }
            displayMenu();
        }
    }
}

