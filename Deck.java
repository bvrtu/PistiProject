public class Deck {
    private Card[] deck;

    public Deck() {
        deck = new Card[52];
        int index = 0;
        for (int s=0;s<4;s++) {
            for (int v=1;v<=13;v++) {
                deck[index] = new Card(s, v);
                index++;
            }
        }
    }

    // Shuffle function.
    public static void shuffleDeck(Card[] deck) {
        for (int i=0;i<deck.length;i++) {
            // Generate a random index.
            int r = i+(int)(Math.random()*(deck.length-i));
            // Swap the elements at indices i and r.
            Card temp = deck[r];
            deck[r] = deck[i];
            deck[i] = temp;
        }
    }

    // Cut function.
    public void cutDeck(int cutPoint) {
        // Create a temporary array to store the first half of the deck
        Card[] temp = new Card[cutPoint];
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
        public Card[] dealCards(int cardsPerPlayer) {
            Card[] hand = new Card[cardsPerPlayer];
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
    public Card[] getDeck() {
        return deck;
    }
}