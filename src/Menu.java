import java.util.Scanner;

public class Menu extends PlayingCardGame {

    public static void displayMenu() {
//        boardInit();
//        JLabel menuLabel = new JLabel("MAIN MENU",JLabel.CENTER);
//        JPanel buttonPanel = new JPanel();
//
//        BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
//        buttonPanel.setLayout(boxLayout);
//        buttonPanel.setBorder(new EmptyBorder(new Insets(45,270,45,270)));
//
//        JButton jb1 = new JButton("Hi Lo");
//        JButton jb2 = new JButton(" Quit ");
//
//        buttonPanel.add(jb1);
//        buttonPanel.add(jb2);
//
//        frame.setLayout(new GridLayout(2,1));
//        frame.add(menuLabel);
//        frame.add(buttonPanel);
//
//        frame.pack();

        // ***********NON GRAPHIC MENU BELOW *********** //

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

