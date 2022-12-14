public class Deck {
    private String[] suits = {"♣", "♦", "♥", "♠"};
    private String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10","J", "Q", "K"};
    private String[] deck;

    public Deck() {
        deck = new String[52];
        for (int i=0;i<ranks.length;i++) {
            for (int j=0;j<suits.length;j++) {
                deck[suits.length*i + j] = suits[j] + ranks[i];
            }
        }
    }

    public static void shuffle(String[] deck) {
        for (int i=0;i<deck.length;i++) {
            int r = i + (int) (Math.random() * (deck.length-i));
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    public String[] getDeck() {
        return deck;
    }
}
