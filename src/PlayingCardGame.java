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

        deckOne.discardPile.add(deckOne.getTopCard());
        deckOne.removeCardFromDeck(deckOne.getTopCard());

        do {
            boolean endOfTurn = false;
            boolean pickAllowed = true;
            while (!endOfTurn) {
                System.out.println();
                if (deckOne.discardPile.size() > 0) {
                    System.out.println("Top of discardpile is: " + deckOne.discardPile.get(deckOne.discardPile.size() - 1).getValue()
                            + " of " + deckOne.discardPile.get(deckOne.discardPile.size() - 1).getSuit());
                }
                if (pileOne.size() > 0) {
                    System.out.println("Top of opponents hand: " + pileOne.get(0).getValue() + " of " + pileOne.get(0).getSuit()); //change to pile Two
                }

                System.out.println("On your hand:");
                for (PlayingCard pC : handOne) System.out.println(pC.getValue() + " of " + pC.getSuit());

                System.out.println();
                //TODO Remove if i menyn, så alla val alltid finns.
                if (pickAllowed) System.out.println("1. Take top card from deck");
                if (handOne.size() > 2) System.out.println("2. Discard card from hand");
                if (checkPairHand(handOne)) System.out.println("3. Put down pair");
                if (deckOne.discardPile.size() > 0) {
                    if (checkPairDiscard(handOne, deckOne.discardPile.get(deckOne.discardPile.size() - 1)))
                        System.out.println("4. Pair with top of discard pile");
                }
                System.out.println("0. End Turn");
                System.out.print("Choice:");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        if (pickAllowed && deckOne.deck.size() > 0) {
                            handOne.add(deckOne.deck.get(0));
                            deckOne.deck.remove(deckOne.deck.get(0));
                            if (handOne.size() > 2) pickAllowed = false;
                        } else System.out.println("Not allowed...");
                        break;
                    case 2:
                        if (handOne.size() > 2) {
                            System.out.println("Choose card to discard!");
                            for (int i = 0; i < handOne.size(); i++)
                                System.out.println((i + 1) + ". " + handOne.get(i).getValue() + " of " + handOne.get(i).getSuit());
                            System.out.print("\nChoice: ");
                            int discChoice = sc.nextInt();
                            sc.nextLine();
                            deckOne.discardPile.add(handOne.get(discChoice - 1));
                            handOne.remove(discChoice - 1);
                            pickAllowed = false;
                        } else System.out.println("Not allowed...");
                        break;
                    case 3:
                        if (checkPairHand(handOne)) {
                            for (int i = 0; i < handOne.size() - 1; i++) {
                                for (int j = i + 1; j < handOne.size(); j++) {
                                    if (handOne.get(i).getValue() == handOne.get(j).getValue()) {
                                        pileOne.add(handOne.get(i));
                                        pileOne.add(handOne.get(j));
                                        handOne.remove(j);
                                        handOne.remove(i);
                                    }
                                }
                            }
                            pickAllowed = true;
                        } else System.out.println("No pair on hand, why did you even...?");
                        break;
                    case 4:
                        if (checkPairDiscard(handOne, deckOne.discardPile.get(deckOne.discardPile.size() - 1))) {
                            for (int i = 0; i < handOne.size(); i++) {
                                if (handOne.get(i).getValue() == deckOne.discardPile.get(deckOne.discardPile.size() - 1).getValue()) {
                                    pileOne.add(handOne.get(i));
                                    pileOne.add(deckOne.discardPile.get(deckOne.discardPile.size() - 1));
                                    handOne.remove(i);
                                    deckOne.discardPile.remove(deckOne.discardPile.size() - 1);
                                    break;
                                }
                            }
                        }
                        break;
                    case 0:
                        if (handOne.size() == 2) {
                            endOfTurn = true;
                        } else System.out.println("Not allowed, incorrect number of cards on hand");
                        break;
                    default:
                        System.out.println("Incorrect selection");
                        break;
                }
            }

            System.out.println("Opponent turn");

        } while (!isDone);

    }

    private static boolean checkPairHand(List<PlayingCard> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getValue() == hand.get(j).getValue())
                    return true;
            }
        }
        return false;
    }

    private static boolean checkPairDiscard(List<PlayingCard> hand, PlayingCard topDisc) {
        for (PlayingCard pC : hand) {
            if (pC.getValue() == topDisc.getValue()) return true;
        }
        return false;
    }

}