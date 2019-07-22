import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PlayingCardDeck {

    List<PlayingCard> deck = new ArrayList<>();
    List<PlayingCard> discardPile = new ArrayList<>();
    private int cardNumber = 0;

    PlayingCardDeck() {
        for (Suit s : Suit.values())
            for (Value v : Value.values())
                deck.add(new PlayingCard(s, v));
    }

    void shuffleDeck() {
        Collections.shuffle(deck);
    }

    PlayingCard getTopCard() {
        return deck.get(0);
    }

    void cardToBottom(PlayingCard insertCard) {
        try {
            deck.remove(insertCard);
        }
        catch (Exception e) {
            System.out.println("Card unavailable...");
        }
        deck.add(insertCard);
    }

    void removeCardFromDeck(PlayingCard removeCard) {
        deck.remove(removeCard);
    }

    void moveToDiscardPile() {
        discardPile.add(deck.get(0));
    }

    int getCardNumber() {
        return cardNumber;
    }

    void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
