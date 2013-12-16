package blackjack;

import blackjack.Card.Face;
import blackjack.Card.Suit;
import java.util.ArrayList;

/**
 * This class takes information from Card class to produce a complete and
 * shuffled 52 card deck.
 *
 * @author Zachary Bunyard & Parker Brown
 *
 */
public class Deck {

    private final ArrayList<Card> CARDS;

    /**
     * Deck of cards
     */
    public Deck() {
        CARDS = new ArrayList<Card>(); // list of CARDS

        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                CARDS.add(new Card(suit, face));
            }
        }
    }

    /**
     * Shuffles the deck
     * @param n
     */
        public void shuffle(int n) {
        int i, j, k;

        for (k = 0; k < n; k++) {
            i = (int) (CARDS.size() * Math.random());  // Pick 2 random cards
            j = (int) (CARDS.size() * Math.random());  // from the deck

            // swap these randomly picked values
            Card temp = CARDS.get(i);
            CARDS.set(i, CARDS.get(j));
            CARDS.set(j, temp);
        }
    }

    /**
     *
     * @return
     */
    public Card draw() {
        Card card = CARDS.get(0);
        CARDS.remove(0);
        return card;
    }

    @Override
    public String toString() {
        String str = "";
        for (Card card : CARDS) {
            str += card.toString() + "\n";
        }
        return str;
    }
}
