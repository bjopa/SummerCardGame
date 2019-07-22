import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class PlayingCardGame {
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
            boolean hasDiscarded = false;
            while (!endOfTurn) {
                int lastDisCard = deckOne.discardPile.size() - 1;
                System.out.println();
                if (deckOne.discardPile.size() > 0) {
                    System.out.println("Top of discardpile is: " + deckOne.discardPile.get(lastDisCard).getValue()
                            + " of " + deckOne.discardPile.get(lastDisCard).getSuit());
                }
                if (pileTwo.size() > 0) {
                    System.out.println("Top of opponents pile: " + pileTwo.get(pileTwo.size() - 1).getValue()
                            + " of " + pileTwo.get(pileTwo.size() - 1).getSuit());
                }
                if (pileOne.size() > 0) {
                    System.out.println("Top of your pile: " + pileOne.get(pileOne.size() - 1).getValue()
                            + " of " + pileOne.get(pileOne.size() - 1).getSuit());
                }

                System.out.println("On your hand:");
                for (PlayingCard pC : handOne) System.out.println(pC.getValue() + " of " + pC.getSuit());

                System.out.println();
                //TODO Remove if i menyn, så alla val alltid finns?
                if (pickAllowed) System.out.println("1. Take top card from deck");
                if (handOne.size() > 2) System.out.println("2. Discard card from hand");
                if (checkPairHand(handOne)) System.out.println("3. Put down pair from hand");
                if (deckOne.discardPile.size() > 0) {
                    if (checkPairDiscard(handOne, deckOne.discardPile.get(lastDisCard)))
                        System.out.println("4. Pair with top of discard pile");
                }
                if (pileTwo.size() > 0) {
                    if (checkPairOpponent(handOne, pileTwo.get(pileTwo.size() - 1)))
                        System.out.println("5. Pair with opponent pile");
                }
                System.out.println("0. End Turn");
                System.out.print("Choice:");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        if (pickAllowed && deckOne.deck.size() > 0 && !hasDiscarded) {
                            handOne.add(deckOne.deck.get(0));
                            deckOne.deck.remove(deckOne.deck.get(0));
                            if (handOne.size() > 2) pickAllowed = false;
                        } else System.out.println("Not allowed...");
                        break;
                    case 2:
                        if (handOne.size() > 2 && !hasDiscarded) {
                            System.out.println("Choose card to discard!");
                            for (int i = 0; i < handOne.size(); i++)
                                System.out.println((i + 1) + ". " + handOne.get(i).getValue() + " of " + handOne.get(i).getSuit());
                            System.out.print("\nChoice: ");
                            int discChoice = sc.nextInt();
                            sc.nextLine();
                            deckOne.discardPile.add(handOne.get(discChoice - 1));
                            handOne.remove(discChoice - 1);
                            pickAllowed = false;
                            hasDiscarded = true;
                        } else System.out.println("Not allowed...");
                        break;
                    case 3:
                        if (checkPairHand(handOne) && !hasDiscarded) {
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
                            if (handOne.size() <= 2) pickAllowed = true;
                        } else System.out.println("No pair on hand, why did you even...?");
                        break;
                    case 4:
                        if (checkPairDiscard(handOne, deckOne.discardPile.get(lastDisCard)) && !hasDiscarded) {
                            for (int i = 0; i < handOne.size(); i++) {
                                if (handOne.get(i).getValue() == deckOne.discardPile.get(lastDisCard).getValue()) {
                                    pileOne.add(handOne.get(i));
                                    pileOne.add(deckOne.discardPile.get(lastDisCard));
                                    handOne.remove(i);
                                    deckOne.discardPile.remove(lastDisCard);
                                    break;
                                }
                            }
                        } else System.out.println("Oops, no pairs there.");
                        if (handOne.size() <= 2) pickAllowed = true;
                        break;
                    case 5:
                        if (checkPairOpponent(handOne, pileTwo.get(pileTwo.size() - 1)) && !hasDiscarded) {
                            Value valueMatch = null;
                            for (int i = 0; i < handOne.size(); i++) {
                                if (handOne.get(i).getValue() == pileTwo.get(pileTwo.size() - 1).getValue()) {
                                    valueMatch = handOne.get(i).getValue();
                                    break;
                                }
                            }
                            do {
                                pileOne.add(pileTwo.get(pileTwo.size() - 1));
                                pileTwo.remove(pileTwo.size() - 1);
                            } while (pileTwo.size() > 0 && pileTwo.get(pileTwo.size() - 1).getValue() == valueMatch);
                        } else System.out.println("Oops, no pairs available.");
                        if (handOne.size() <= 2) pickAllowed = true;
                        break;
                    case 0:
                        if (handOne.size() == 2 && hasDiscarded) {
                            endOfTurn = true;
                        } else System.out.println("Not allowed, incorrect number of cards on hand");
                        break;
                    default:
                        System.out.println("Incorrect selection");
                        break;
                }
            }

            //************************PLAYER2************************************************
            System.out.println("Opponent played as follows:");
            boolean pickAllowed2 = true;
            boolean p2Done;
            do {
                int lastDisCard = deckOne.discardPile.size() - 1;
                p2Done = true;
                //***************************PAIR WITH OPP***********************************
                if (pileOne.size() > 0) {
                    if (checkPairOpponent(handTwo, pileOne.get(pileOne.size() - 1))) {
                        System.out.println("Opp Pair Player Start");
                        Value valueMatch = null;
                        for (int i = 0; i < handTwo.size(); i++) {
                            if (handTwo.get(i).getValue() == pileOne.get(pileOne.size() - 1).getValue()) {
                                valueMatch = handTwo.get(i).getValue();
                                System.out.println("before break");
                                break;
                            }
                        }
                        System.out.println("before do");
                        do {
                            pileTwo.add(pileOne.get(pileOne.size() - 1));
                            pileOne.remove(pileOne.size() - 1);
                        } while (pileOne.size() > 0 && pileOne.get(pileOne.size() - 1).getValue() == valueMatch);
                        if (handTwo.size() < 2) pickAllowed2 = true;
                        p2Done = false;
                        System.out.println("Opp Pair Player Done");
                    }
                }
                //************************PAIR ON HAND?*************************************
                if (checkPairHand(handTwo)) {
                    System.out.println("Opp Pair Hand");
                    for (int i = 0; i < handTwo.size() - 1; i++) {
                        for (int j = i + 1; j < handTwo.size(); j++) {
                            if (handTwo.get(i).getValue() == handTwo.get(j).getValue()) {
                                pileOne.add(handTwo.get(i));
                                pileOne.add(handTwo.get(j));
                                handTwo.remove(j);
                                handTwo.remove(i);
                            }
                        }
                    }
                    if (handTwo.size() <= 2) pickAllowed2 = true;
                    p2Done = false;
                }
                //******************************PAIR WITH DISCARD************************************
                if (deckOne.discardPile.size() > 0) {
                    if (checkPairDiscard(handTwo, deckOne.discardPile.get(deckOne.discardPile.size() - 1))) {
                        System.out.println("Opp Pair Disc");
                        for (int i = 0; i < handTwo.size(); i++) {
                            if (handTwo.get(i).getValue() == deckOne.discardPile.get(lastDisCard).getValue()) {
                                pileTwo.add(handTwo.get(i));
                                pileTwo.add(deckOne.discardPile.get(lastDisCard));
                                handTwo.remove(i);
                                deckOne.discardPile.remove(lastDisCard);
                                break;
                            }
                        }
                        if (handTwo.size() <= 2) pickAllowed2 = true;
                        p2Done = false;
                    }
                }
                //***************************PICK UP CARD*************************************
                if (pickAllowed2 && deckOne.deck.size() > 0) {
                    System.out.println("Picked from Deck");
                    handTwo.add(deckOne.deck.get(0));
                    deckOne.deck.remove(deckOne.deck.get(0));
                    if (handTwo.size() > 2) pickAllowed2 = false;
                    p2Done = false;
                }
                //******************************THROW CARD, DONE!************************************
            } while (!p2Done);
            Random rand = new Random();
            int randomIndex = rand.nextInt(handTwo.size() - 1) + 1;
            deckOne.discardPile.add(handTwo.get(randomIndex));
            handTwo.remove(randomIndex);

            //************************ENDPLAYER2*****************************
            System.out.println("Cards remaining: " + deckOne.deck.size());
            if (deckOne.deck.size() == 0) isDone = true;

        } while (!isDone);

        int pointsPlayer = 0, pointsOpp = 0;
        for (PlayingCard pC : pileOne) {
            pointsPlayer += 5;
            if (pC.getNumValue() > 10) pointsPlayer += 5;
        }
        for (PlayingCard pC : pileTwo) {
            pointsOpp += 5;
            if (pC.getNumValue() > 10) pointsOpp += 5;
        }

        System.out.println("Player - Computer: " + pointsPlayer + " - " + pointsOpp);

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

    private static boolean checkPairOpponent(List<PlayingCard> hand, PlayingCard topOpp) {
        for (PlayingCard pC : hand) {
            if (pC.getValue() == topOpp.getValue()) return true;
        }
        return false;
    }

}