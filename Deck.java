package blackjack;

public class Deck {

    public static final int NCARDS = 52;

    private Card[] deck;         // Contains all 52 cards
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

            // swap these randomly picked cards
            Card tmp = deck[i];
            deck[i] = deck[j];
            deck[j] = tmp;
        }

        currentCard = 0;   // Reset current card to deal
    }

    // deal(): deal deck[currentCard] out
    // TODO: remove card dealt from stack
    public Card deal() {
        if (currentCard < NCARDS) {
            return (deck[ currentCard++]);
        } else { // TODO: shuffle and deal
            System.out.println("Out of cards error");
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
