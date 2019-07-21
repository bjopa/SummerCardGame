public enum Suit {
    CLUBS(168),
    DIAMONDS(169),
    HEARTS(170),
    SPADES(171);

    public int symbolValue;

    Suit(int symbolValue) {
        this.symbolValue = symbolValue;
    }

    public int getSymbolValue() {
        return symbolValue;
    }
}
