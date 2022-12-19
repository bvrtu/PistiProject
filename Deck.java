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

    // Shuffle function.
    public static void shuffleDeck(String[] deck) {
        for (int i=0;i<deck.length;i++) {
            // Generate a random index.
            int r = i+(int)(Math.random()*(deck.length-i));
            // Swap the elements at indices i and r.
            String temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    // Cut function.
    public void cutDeck(int cutPoint) {
        // Create a temporary array to store the first half of the deck
        String[] temp = new String[cutPoint];
            for (int i=0;i<cutPoint;i++) {
                temp[i] = deck[i];
            }
        // Shift the second half of the deck to the front of the deck
            for (int i=0;i<deck.length-cutPoint;i++) {
                deck[i] = deck[cutPoint+i];
            }
    
        // Add the first half of the deck to the end of the deck
            for (int i=0;i<cutPoint;i++) {
                deck[deck.length-cutPoint+i] = temp[i];
            }
        }

        // Deal function.
        public String[] dealCards(int cardsPerPlayer) {
            String[] hand = new String[cardsPerPlayer];
            for (int i=0;i<cardsPerPlayer;i++) {
                hand[i] = deck[i];
            }
            // Shift the elements in the deck array to the left by the number of cards dealt.
            for (int i=0; i<deck.length-cardsPerPlayer; i++) {
                deck[i] = deck[i+cardsPerPlayer];
            }
            // Set the last few elements of the deck array to null to remove the cards that have been dealt.
            for (int i=deck.length-cardsPerPlayer; i<deck.length; i++) {
                deck[i] = null;
            }
            return hand;
        }    

        // To call the deck in another class, this function created.
    public String[] getDeck() {
        return deck;
    }
}