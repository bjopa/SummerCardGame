import java.awt.*;

public class PlayingCard {

    private Suit suit;
    private Value value;
    private Boolean isFaceUp;
    private String fileName="";
    //public Image faceImg = Toolkit.getDefaultToolkit().getImage(fileName);
    public Image faceImg = Toolkit.getDefaultToolkit().getImage("D3.jpg");

    PlayingCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        isFaceUp = false;
        setFileName();
    }

    public Boolean getFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(Boolean faceUp) {
        isFaceUp = faceUp;
    }

    Suit getSuit() {
        return suit;
    }

    Value getValue() {
        return value;
    }

    int getNumValue() {
        return value.getValue();
    }

    public void setFileName() {
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

    public String getFileName() {
        return fileName;
    }
}
