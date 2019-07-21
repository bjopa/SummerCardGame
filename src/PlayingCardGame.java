import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PlayingCardGame extends grafik {
    private static int points = 0;
    private static Scanner sc = new Scanner(System.in);

    static void highOrLow() {
        PlayingCardDeck deckOne = new PlayingCardDeck();
        deckOne.shuffleDeck();
        String response = null;
        boolean cont = true;

        System.out.println("\nRules: Guess if the next card is Higher or Lower than the current");
        System.out.println("Guess by typing 'h' for Higher or 'l' for Lower");
        System.out.println("\nGood Luck!");

        do {
            //next round, if deck contains another card
            if (deckOne.deck.size() > 0) {
                deckOne.showTopCard();

                //checks guess from last card
                if (deckOne.discardPile.size() > 0) {
                    int diff = (deckOne.deck.get(0).getNumValue() - deckOne.discardPile.get(deckOne.discardPile.size() - 1).getNumValue());

                    if (diff > 0 && response.equals("h")) System.out.println("CORRECT, points: " + (++points));
                    else if (diff == 0) System.out.println("CORRECT (lucky Bastard...), points: " + (++points));
                    else if (diff < 0 && response.equals("l")) System.out.println("CORRECT, points: " + (++points));
                    else {
                        System.out.println("WRONG, points: " + points);
                        deckOne.discardPile.addAll(deckOne.deck);
                        deckOne.deck.clear();
                    }

                }

                //make a guess if there's a next card (or not game over = deck emptied above)
                if (deckOne.discardPile.size() < 51) {
                    do {
                        System.out.print("Is next card higher(h) or lower(l)?: ");
                        response = sc.nextLine().toLowerCase();
                    } while (!(response.equals("h") || response.equals("l")));
                    deckOne.moveToDiscardPile();
                } else System.out.println("Game Over!");

                //empty deck
            } else {
                System.out.print("\nYou got " + points + " points.\nWould you like to play again? (y/n)?: ");
                String choice = sc.nextLine().toLowerCase();
                if (choice.equals("y")) {
                    points = 0;
                    System.out.print("\nShuffling all cards, press ENTER to deal a first card!");
                    deckOne.deck.addAll(deckOne.discardPile);
                    deckOne.shuffleDeck();
                    deckOne.discardPile.clear();
                    deckOne.setCardNumber(1);
                    sc.nextLine();
                } else cont = false;
            }

        } while (cont);

    }

    static void hugo() {
        boolean isDone = false;

        PlayingCardDeck deckOne = new PlayingCardDeck();
        deckOne.shuffleDeck();

        List<PlayingCard> handOne = new ArrayList<>();
        List<PlayingCard> handTwo = new ArrayList<>();
        List<PlayingCard> pileOne = new ArrayList<>();
        List<PlayingCard> pileTwo = new ArrayList<>();


        for (int i = 0; i < 2; i++) {
            handOne.add(deckOne.deck.get(0));
            deckOne.deck.remove(deckOne.deck.get(0));
            handTwo.add(deckOne.deck.get(0));
            deckOne.deck.remove(deckOne.deck.get(0));
        }

        do {
            boolean endOfTurn = false;
            boolean pickAllowed = true;
            while (!endOfTurn) {
                System.out.println("\nTop of pile is: " + deckOne.deck.get(0).getValue() + " of " + deckOne.deck.get(0).getSuit());

                if (pileOne.size() > 0) {
                    System.out.println("Top of opponents hand: " + pileOne.get(0)); //change to pile Two
                }

                System.out.println("On your hand:");
                for (PlayingCard pC : handOne) System.out.println(pC.getValue() + " of " + pC.getSuit());

                checkPair(handOne);

                System.out.println("\n1. Take top card from pile");
                System.out.println("2. Discard card from hand");
                if (checkPair(handOne)) System.out.println("3. Put down pair");
                System.out.println("0. End Turn");
                System.out.println("Choice:");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        if (pickAllowed) {
                            handOne.add(deckOne.deck.get(0));
                            deckOne.deck.remove(deckOne.deck.get(0));
                            pickAllowed=false;
                        } else System.out.println("Not allowed...");
                        break;
                    case 2:
                        pileOne.add(handOne.get(0));
                        handOne.remove(0);
                        break;
                    case 3: if (checkPair(handOne)) System.out.println("Putting it down..");
                        else System.out.println("No pair on hand, why did you even ..?");
                        break;
                    case 0:
                        if (handOne.size()==2) {
                            endOfTurn = true;
                        }
                        else System.out.println("Not allowed, incorrect number of cards on hand");
                        break;
                    default:
                        System.out.println("Incorrect selection");
                        break;
                }
            }

            System.out.println("Opponent turn");

        } while (!isDone);

    }

    static boolean checkPair(List<PlayingCard> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getValue() == hand.get(j).getValue())
                    return true;
            }
        }
        return false;
    }

}