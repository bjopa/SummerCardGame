import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PlayingCardDeck {

    //skapar två listor, en för själva leken och en för tillhörande slänghög
    List<PlayingCard> deck = new ArrayList<>();
    List<PlayingCard> discardPile = new ArrayList<>();
    //korträknare, räknar vilken omgång man är på.
    private int cardNumber = 0;

    PlayingCardDeck() {
        //skapar kortleken, lägger till ett kort för varje
        //kombination av färg och valör
        for (Suit s : Suit.values())
            for (Value v : Value.values())
                deck.add(new PlayingCard(s, v));
    }

    void shuffleDeck() {
        //blandar om kortleken
        Collections.shuffle(deck);
    }

    PlayingCard getTopCard() {
        //hämtar översta kortet i leken
        return deck.get(0);
    }

    void cardToBottom(PlayingCard insertCard) {
        //flytta ett visst kort till botten
        //om det finns ett sådant i leken
        //annars läggs det bara till i botten
        try {
            deck.remove(insertCard);
        } catch (Exception e) {
            System.out.println("Card unavailable...");
        }
        deck.add(insertCard);
    }

    void removeCardFromDeck(PlayingCard removeCard) {
        //ta bort ett kort ur leken
        deck.remove(removeCard);
    }

    void moveToDiscardPile() {
        //flytta ett kort till DiscardPile
        discardPile.add(deck.get(0));
    }

    int getCardNumber() {
        return cardNumber;
    }

    void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}