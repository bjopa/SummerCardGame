public class PlayingCard {

    private Suit suit;
    private Rank rank;
    private Boolean isFaceUp;
    //front face image

    PlayingCard(Suit suit, Rank rank) {
        this.suit=suit;
        this.rank=rank;
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

    Rank getRank() {
        return rank;
    }
}
