import java.util.Scanner;
import java.util.Random;

public class Game {   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        Card[] cards = deck.getDeck();
        Card[] player = new Card[4];
        Card[] computer = new Card[4];
        Card[] table = new Card[52];
        Card[] player2 = new Card[52];
        Card[] computer2 = new Card[52];
        String user = "";
        String cutpoint = "";
        int turn = 0;

        System.out.println("--------------------------\nWelcome to the Pişti game!\n--------------------------\nIf you are ready, what is your username?");
        user = sc.nextLine();

        System.out.println("--------------------------\nCreating deck...\nShuffling...");
        deck.shuffleDeck(cards);

        while (true) {
            System.out.println("Please select a cutpoint to cut the deck!");
            cutpoint = sc.nextLine();
          
            try {
              int cutpointInt = Integer.parseInt(cutpoint);
              if (cutpointInt < 1 || cutpointInt > 52) {
                System.out.println("Error: Cutpoint must be between 1 and 52.");
              } else {
                deck.cutDeck(cutpointInt);
                break;
              }
            } catch (NumberFormatException e) {
              System.out.println("Error: Cutpoint must be an integer.");
            }
        }

        System.out.println("Game is starting...\nCards are dealing...");
        while (turn < 6) {
            if (turn > 0) {
                player = new Card[4];
                computer = new Card[4];
            }
        for (int i=0;i<4;i++) {
            player[i] = deck.dealCards(1)[0];
            computer[i] = deck.dealCards(1)[0];
        }
            if (turn == 0) {
            table = deck.dealCards(4);
            }

            System.out.println("--------------------------");

            while (player.length > 0) {

            System.out.print("Cards on the table: ");
            for (int i=0;i<table.length;i++) {
                if (table[i] != null) {
                System.out.print(table[i] + " ");
                }
            }
            
            System.out.println();
            System.out.println("--------------------------");
            
            while (player.length > 0 && computer.length > 0) {
                System.out.print("Your cards: ");
                    for (int i=0;i<player.length;i++) {
                        System.out.print(player[i] + " ");
                    }
                    System.out.println();
                    System.out.println("--------------------------");
                // Prompt the user for input and allow them to play a card
                System.out.print("Enter the index of the card you want to play: ");
                String input = sc.nextLine();
                
                // Try to parse the input as an integer
                try {
                    int index = Integer.parseInt(input)-1;
                    if (index < 0 || index >= player.length) {
                        System.out.println("Invalid index. Please enter an index between 1 and " + (player.length) + ".");
                    } else {    
                        if (player[index].getValue() == table[0].getValue() || player[index].getValue() == 11) {
                            // Create a new array for player2's hand with one extra element
                            Card[] newPlayer2 = new Card[player2.length + table.length + 1];
                            // Add the thrown card to the beginning of player2's new hand
                            newPlayer2[0] = player[index];
                            // Add all elements from the table to player2's new hand, starting at index 1
                            System.arraycopy(table, 0, newPlayer2, 1, table.length);
                            // Add all elements from player2's old hand to the new one, starting at the end of the table
                            System.arraycopy(player2, 0, newPlayer2, table.length + 1, player2.length);
                            // Replace player2's old hand with the new one
                            player2 = newPlayer2;
                        
                            // Create a new array for the player's hand with one less element
                            Card[] newPlayer = new Card[player.length - 1];
                            // Copy all elements from the old hand to the new one, except for the selected card
                            System.arraycopy(player, 0, newPlayer, 0, index);
                            System.arraycopy(player, index + 1, newPlayer, index, player.length - index - 1);
                            // Replace the old hand with the new one
                            player = newPlayer;
                            // Clear the table
                            table = new Card[52];
                            for (int i=0;i<player2.length;i++) {
                                System.out.print(player2[i]);
                            }                     
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
                        Card[] newPlayer = new Card[player.length-1];
                        // Copy all elements from the old hand to the new one, except for the selected card
                        System.arraycopy(player, 0, newPlayer, 0, index);
                        System.arraycopy(player, index+1, newPlayer, index, player.length-index-1);
                        // Replace the old hand with the new one
                        player = newPlayer;
                        }
                        
                        // Print the cards on the table
                        System.out.println("--------------------------");
                        System.out.print("Cards on the table: ");
                        for (int i = 0;i<table.length;i++) {
                            if (table[i] != null) {
                            System.out.print(table[i] + " ");
                            }
                        }
                        System.out.println();

                        Card cardToPlay = null;
                        for (Card card : computer) {
                            if (table[0] != null) {
                            if (card.getValue() == (table[0].getValue())) {
                                cardToPlay = card;
                                break;
                            }
                        }
                    }
                        if (cardToPlay == null) {
                            // Choose a random card if no matching cards were found
                            Random rd = new Random();
                            int index2 = rd.nextInt(computer.length);
                            cardToPlay = computer[index2];
                        }
                            // Remove the chosen card from the computer's hand and add it to the table
                            Card[] newComputer = new Card[computer.length-1];
                            int index2 = -1;
                            for (int i = 0; i < computer.length; i++) {
                                if (computer[i] == cardToPlay) {
                                    index2 = i;
                                    break;
                                }
                            }
                            System.arraycopy(computer, 0, newComputer, 0, index2);
                            System.arraycopy(computer, index2+1, newComputer, index2, computer.length-index2-1);
                            computer = newComputer;
                            
                            // Add the chosen card to the table
                            Card[] newTable2 = new Card[table.length+1];
                            newTable2[0] = cardToPlay;
                            System.arraycopy(table, 0, newTable2, 1, table.length);
                            table = newTable2;
                            
                            // Print the cards on the table
                            System.out.println("--------------------------");
                            System.out.print("Computer played. Cards on the table: ");
                            for (int i=0;i<table.length;i++) {
                                if (table[i] != null) {
                                System.out.print(table[i] + " ");
                                }
                            }
                            System.out.println();
                            System.out.println("--------------------------");
                        }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
        }
        turn++;
    }
}
}