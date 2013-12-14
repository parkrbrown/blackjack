package blackjack;
/**
 * This class takes information from Card class to produce a complete and shuffled 52 card deck.
 * @author Zachary Bunyard & Parker Brown
 *
 */

public class Deck {

    public static final int NCARDS = 52;

    private Card[] deck;         // Contains all 52 cards
    int[] deckValue = {11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10,11,2,3,4,5,6,7,8,9,10,10,10,10};
    private int currentCard;            // deal THIS card in deck

    public Deck() {
        deck = new Card[NCARDS];

        int i = 0;

        for (int suit = Card.DIAMOND; suit <= Card.SPADE; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                deck[i++] = new Card(suit, rank);
            }
        }

        currentCard = 0;
    }

    // shuffle(n): shuffle the deck
    public void shuffle(int n) {
        int i, j, k;

        for (k = 0; k < n; k++) {
            i = (int) (NCARDS * Math.random());  // Pick 2 random cards
            j = (int) (NCARDS * Math.random());  // in the deck
            
            // swap these randomly picked values
            int temp = deckValue[i];
            deckValue[i] = deckValue[j];
            deckValue[j] = temp;

            // swap these randomly picked cards
            Card tmp = deck[i];
            deck[i] = deck[j];
            deck[j] = tmp;
        }

        currentCard = 0;   // Reset current card to deal
    }
    public int dealValue() {
    	if (currentCard < NCARDS) {
    		return (deckValue[ currentCard]);
    	} else {
    		currentCard = 0;;
    		return (0);
    	}
    }

    public Card deal() {
        if (currentCard < NCARDS) {
            return (deck[ currentCard++]);
        } else {
            currentCard = 0;
            return (null);  // Error;
        }
    }

    public String toString() {
        String s = "";
        int k;

        k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                s += (deck[k++] + " ");
            }

            s += "\n";
        }
        return (s);
    }
}
