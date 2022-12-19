import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        String[] cards = deck.getDeck();
        String[] player = new String[52];
        String[] computer = new String[52];
        String[] table = new String[52];
        String user = "";

        System.out.println("--------------------------\nWelcome to the pişti game!\n--------------------------\nIf you are ready, what is your username?");
        user = sc.nextLine();

        System.out.println("--------------------------\nDeck is creating...\nDeck is shuffling...\nPlease select a cutpoint to cut the deck!");
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
        }
    }