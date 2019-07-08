import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCardDeck {

    List<PlayingCard> deck = new ArrayList<>();
    List<PlayingCard> discardPile = new ArrayList<>();
    private int cardNumber = 1;

    PlayingCardDeck() {
        for (Suit s : Suit.values())
            for (Value v : Value.values())
                deck.add(new PlayingCard(s, v));
    }

    void shuffleDeck() {
        Collections.shuffle(deck);
    }

    void playTopCard() {
        System.out.println("\nCard " + cardNumber + " played: " + deck.get(0).getValue() + " of " + deck.get(0).getSuit());
        cardNumber++;
    }

    void cardToBottom() {

    }

    void moveToDiscardPile() {
        discardPile.add(deck.get(0));
        deck.remove(0);
    }

    void printDeck() {
        for (int i = 0; i < deck.size(); i++)
            System.out.println("C" + (i + 1) + "= " + deck.get(i).getValue() + " " + deck.get(i).getSuit());
    }

    public int getCardNumber() {
        return cardNumber;
    }

    void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
