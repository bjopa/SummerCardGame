public class Main {

    public static void main(String[] args) {
	    PlayingCardDeck deckOne = new PlayingCardDeck();
        deckOne.shuffleDeck();
        deckOne.printDeck();
        System.out.println("Picked card:" + deckOne.deck.get(22).getRank() + " " + deckOne.deck.get(22).getSuit());
    }

}