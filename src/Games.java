import java.util.Scanner;

class Games {
    //poängräknare
    private static int points = 0;

    static void highOrLow() {
        //skapa två sfx objekt, ett per ljudfil
        //sfx classen är i stort sett bara "snodd"
        Sfx corr = new Sfx("correct.wav");
        Sfx incorr = new Sfx("incorrect.wav");

        //initiering, scanner, ny kortlek, kontrollvariabler
        Scanner sc = new Scanner(System.in);
        PlayingCardDeck deckOne = new PlayingCardDeck();
        deckOne.shuffleDeck();
        String response = null;
        boolean cont = true;

        System.out.println("\nGood Luck!");

        do {
            //ny runda, så länge leken har kvar kort
            if (deckOne.deck.size() > 0) {
                deckOne.setCardNumber(deckOne.getCardNumber() + 1);
                PlayingCard top = deckOne.getTopCard();
                System.out.println("\nCard " + deckOne.getCardNumber() + " is " + top.getValue() + " of " + top.getSuit());

                //kolla nya kortet mot gissningen på förra, görs ej första varvet
                if (deckOne.discardPile.size() > 0) {
                    int diff = (top.getNumValue() - deckOne.discardPile.get(deckOne.discardPile.size() - 1).getNumValue());

                    // om diff är positiv och gissning högre
                    if (diff > 0 && response.equals("h")) {
                        Thread sfx = new Thread(corr);
                        sfx.start();
                        System.out.println("CORRECT, points: " + (++points));
                        //om de två korten har samma värde
                    } else if (diff == 0) {
                        Thread sfx = new Thread(corr);
                        sfx.start();
                        System.out.println("CORRECT (lucky Bastard...), points: " + (++points));
                        //om diff är negativ och gissning lägre
                    } else if (diff < 0 && response.equals("l")) {
                        Thread sfx = new Thread(corr);
                        sfx.start();
                        System.out.println("CORRECT, points: " + (++points));
                        //annars var det ju fel...
                    } else {
                        Thread sfx = new Thread(incorr);
                        sfx.start();
                        System.out.println("WRONG, points: " + points);
                        deckOne.discardPile.addAll(deckOne.deck);
                        deckOne.deck.clear();
                    }

                }

                //om det går att spela ett vrv till, gör en ny gissning
                if (deckOne.discardPile.size() < 51) {
                    do {
                        System.out.print("Is next card higher(h) or lower(l)?: ");
                        response = sc.nextLine().toLowerCase();
                    } while (!(response.equals("h") || response.equals("l")));
                    deckOne.moveToDiscardPile();
                    deckOne.removeCardFromDeck(top);
                    //annars är spelet slut
                } else System.out.println("Game Over!");
                //frågar om man vill spela en ny runda
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
                    //annars avslutas spelet och man kommer tillbaka till menyn
                } else cont = false;
                System.out.println();
            }

        } while (cont);

    }

}
