public class Card {
    public int diamonds = 0;
    public int clubs = 1;
    public int spades = 2;
    public int hearts = 3;
    
    private int value;
    private int suit;
    
    // Creates a new instance of Card.
    
    public Card(int s, int v) {
        value = v;
        suit = s;
    }

    public int getValue() {
        return value;
    }
    
    public String getValueStr() {
        switch (value) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(value);
        }
    }    

    public int getSuit() {
        return suit;
    }

    public String getSuitStr() {
        String[] suitStrings = {"♦", "♣", "♠", "♥"};
        return suitStrings[suit];
    }    

    public String toString() {
        return getSuitStr() + getValueStr();
    }
}