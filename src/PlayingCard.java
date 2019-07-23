import java.awt.*;

public class PlayingCard {

    private Suit suit;
    private Value value;
    private Boolean isFaceUp;

    //3 raderna nedan skulle använts om jag hade haft grafik
    private String fileName="";
    public Image faceImg = Toolkit.getDefaultToolkit().getImage(fileName);

    //konstruktor, tilldela värden enligt enums
    PlayingCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        isFaceUp = false;
        //raden nedan används om jag hade grafik
        setFileName();
    }

    public Boolean getFaceUp() {
        //kan tala om ifall ett kort är uppvänt eller inte, används ej
        return isFaceUp;
    }

    public void setFaceUp(Boolean faceUp) {
        //välj om kort är uppvänt eller ej, används ej
        isFaceUp = faceUp;
    }

    Suit getSuit() {
        //skicka "färg" på aktuellt kort
        return suit;
    }

    Value getValue() {
        //skicka valör på aktuellt kort
        return value;
    }

    int getNumValue() {
        //skicka ett numeriskt värde för aktuellt kort
        return value.getValue();
    }

    private void setFileName() {
        //kollar av vilken "färg" kortet har och påbörjar filnamnet
        //på bilden som ska visas vid grafik
        switch (suit) {
            case DIAMONDS:
                fileName += "D";
                break;
            case SPADES:
                fileName += "S";
                break;
            case HEARTS:
                fileName += "H";
                break;
            case CLUBS:
                fileName += "C";
                break;
        }
        //kollar vilken valör kortet har och avslutar filnamnet
        //på bilden som ska visas vid grafik
        switch (value) {
            case TWO: fileName += "2.jpg"; break;
            case THREE: fileName += "3.jpg"; break;
            case FOUR: fileName += "4.jpg"; break;
            case FIVE: fileName += "5.jpg"; break;
            case SIX: fileName += "6.jpg"; break;
            case SEVEN: fileName += "7.jpg"; break;
            case EIGHT: fileName += "8.jpg"; break;
            case NINE: fileName += "9.jpg"; break;
            case TEN: fileName += "10.jpg"; break;
            case JACK: fileName += "J.jpg"; break;
            case QUEEN: fileName += "Q.jpg"; break;
            case KING: fileName += "K.jpg"; break;
            case ACE: fileName += "A.jpg"; break;
        }
    }

}
