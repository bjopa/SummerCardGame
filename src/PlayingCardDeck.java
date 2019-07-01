import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingCardDeck {

    List<PlayingCard> deck = new ArrayList<>();

    PlayingCardDeck() {
        for (Suit s : Suit.values())
            for (Value v : Value.values())
                deck.add(new PlayingCard(s, v));
    }

    void playTopCard() {

    }

    void cardToBottom() {

    }

    void shuffleDeck() {
        Collections.shuffle(deck);
    }

    void printDeck() {
        for (int i = 0 ; i < deck.size() ; i++ )
            System.out.println("C"+(i+1)+"= " + deck.get(i).getValue() + " " + deck.get(i).getSuit());
    }

}
