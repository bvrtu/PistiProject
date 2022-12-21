import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        Card[] cards = deck.getDeck();
        Card[] player = new Card[4];
        Card[] computer = new Card[4];
        Card[] table = new Card[52];
        String user = "";

        System.out.println("--------------------------\nWelcome to the Pişti game!\n--------------------------\nIf you are ready, what is your username?");
        user = sc.nextLine();

        System.out.println("--------------------------\nCreating deck...\nShuffling...\nPlease select a cutpoint to cut the deck!");
        deck.shuffleDeck(cards);
        user = sc.nextLine();
        deck.cutDeck(Integer.parseInt(user)); 

        System.out.println("Game is starting...\nCards are dealing...");
        for (int i=0;i<4;i++) {
            player[i] = deck.dealCards(1)[0];
            computer[i] = deck.dealCards(1)[0];
        }
            table = deck.dealCards(4);

            System.out.println("--------------------------");

            while (player.length > 0) {

            System.out.print("Cards on the table: ");
            for (int i=0;i<4;i++) {
                System.out.print(table[i] + " ");
            }
            
            System.out.println();
            System.out.println("--------------------------");
            
            System.out.print("Your cards: ");
            for (int i=0;i<4;i++) {
                System.out.print(player[i] + " ");
            }

            System.out.println();
            System.out.println("--------------------------");
            
            while (player.length > 0) {
                // Prompt the user for input
                System.out.print("Enter the index of the card you want to play: ");
                String input = sc.nextLine();
                
                // Try to parse the input as an integer
                try {
                    int index = Integer.parseInt(input)-1;
                    if (index < 0 || index >= player.length) {
                        System.out.println("Invalid index. Please enter an index between 1 and " + (player.length) + ".");
                    } else {
                        // Create a new array for the table with an extra element
                        Card[] newTable = new Card[table.length+1];
                        // Add the selected card to the beginning of the new table
                        newTable[0] = player[index];
                        // Copy all elements from the old table to the new one, starting at index 1
                        System.arraycopy(table, 0, newTable, 1, table.length);
                        // Replace the old table with the new one
                        table = newTable;
                        
                        // Create a new array for the player's hand with one less element
                        Card[] newPlayer = new Card[player.length - 1];
                        // Copy all elements from the old hand to the new one, except for the selected card
                        System.arraycopy(player, 0, newPlayer, 0, index);
                        System.arraycopy(player, index+1, newPlayer, index, player.length-index-1);
                        // Replace the old hand with the new one
                        player = newPlayer;
                        
                        // Print the cards on the table
                        System.out.println("--------------------------");
                        System.out.print("Cards on the table: ");
                        for (int i=0;i<table.length;i++) {
                            System.out.print(table[i] + " ");
                        }
                        System.out.println();
                        System.out.println("--------------------------");
                        
                        // Print the player's hand
                        System.out.print("Your cards: ");
                        for (int i = 0; i < player.length; i++) {
                            System.out.print(player[i] + " ");
                        }
                        System.out.println();
                        System.out.println("--------------------------");
                    }                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }            
            }
        }
    }
}