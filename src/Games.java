import java.util.Scanner;

class Games {
    private static int points = 0;

    static void highOrLow() {
        Scanner sc = new Scanner(System.in);
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
                deckOne.setCardNumber(deckOne.getCardNumber() + 1);
                PlayingCard top = deckOne.getTopCard();
                System.out.println("\nCard " + deckOne.getCardNumber() + " is " + top.getValue() + " of " + top.getSuit());

                //checks guess from last card
                if (deckOne.discardPile.size() > 0) {
                    int diff = (top.getNumValue() - deckOne.discardPile.get(deckOne.discardPile.size() - 1).getNumValue());

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
                    deckOne.removeCardFromDeck(top);
                } else System.out.println("Game Over!");

                //else if empty deck
            } else {
                System.out.print("\nYou got " + points + " points.\nWould you like to play again? (y/n)?: ");
                String choice = sc.nextLine().toLowerCase();
                if (choice.equals("y")) {
                    points = 0;
                    System.out.print("\nShuffling all cards, press ENTER to deal a first card!");
                    deckOne.deck.addAll(deckOne.discardPile);
                    deckOne.shuffleDeck();
                    deckOne.discardPile.clear();
                    deckOne.setCardNumber(0);
                    sc.nextLine();
                } else cont = false;
            }

        } while (cont);

    }


}
