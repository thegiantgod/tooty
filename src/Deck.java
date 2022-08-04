import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList deck = new ArrayList<Card>();

    public static final int DECK_SIZE = 33;
    public static final int NUMBER_COLORS = 9;
    public static final int NUMBER_SIZE = 5;

    public Deck() {                         // méthode de construction
        for (int i = 0; i<NUMBER_COLORS; i++) {
            this.deck.add(new Card("Bleu"));
        }
        for (int i = 0; i<NUMBER_COLORS; i++) {
            this.deck.add(new Card("Rouge"));
        }
        for (int i = 0; i<NUMBER_SIZE; i++) {
            this.deck.add(new Card("Taille 1"));
        }
        for (int i = 0; i<NUMBER_SIZE; i++) {
            this.deck.add(new Card("Taille 2"));
        }
        for (int i = 0; i<NUMBER_SIZE; i++) {
            this.deck.add(new Card("Taille 3"));
        }
        assert (this.deck.size() == DECK_SIZE);
        // System.out.println(cmpt);
    }

    public Card getCard(int i) {                 //obtient la carte au rang i
        return (Card) deck.get(i);
    }
    public int getTaillePaquet ( ) {
        return DECK_SIZE;
    }

    public String Tirer_Carte() {                   // tire une carte au hasard dans le paquet
        boolean success = false;
        Random rand = new Random();
        int j =0;
        while (!success){
            int i = rand.nextInt(DECK_SIZE);
            j=i;
            this.getCard(i).isDrawn();
            if (!this.getCard(i).isDrawn()){
                success = true;
            }
        }
        return this.getCard(j).drawCard();
    }
}
