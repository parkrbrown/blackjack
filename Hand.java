package blackjack;

public class Hand {

    private Card cards[];
    private int numCards;
    private int totalValue;

    public Hand() {
        cards = new Card[numCards];
        numCards = 0;
    }

    public void addCard(Card aCard) {
            cards[numCards++] = aCard;
    }

    public void totalValue() {
        int handValue = 0;

        for (Card cards : cards) {
             //get the rank values to appear then print them off
            //Add up all values of cards and display the total
            totalValue = handValue + cards.rank(); //This was just a test to see if calling up the value of card C would work

        }
        System.out.println("The total value of all the cards is: " + totalValue);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < numCards; ++i) {
            s += "\n" + cards[i];
        }
        return s;
    }
    
    public static void main(String args[]){
        Hand hand = new Hand();
        System.out.println(hand);
    }
}