public class PlayingCard {

    private Suit suit;
    private Value value;
    private Boolean isFaceUp;
    //front face image

    PlayingCard(Suit suit, Value value) {
        this.suit=suit;
        this.value = value;
        isFaceUp = false;
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
}
