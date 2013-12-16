package blackjack;

public class TestDeck {

    /**
     * Tests the deck class. Prints out an un-shuffled deck, then a shuffled deck.
     * @param args
     */
    public static void main(String[] args) {

        Deck deck = new Deck();

        System.out.println(deck);

        deck.shuffle(1000);

        System.out.println(deck);

    }
}
