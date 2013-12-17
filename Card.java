package blackjack;

/**
 * This contains the information to produce cards you would find in a 52 card
 * deck.
 *
 * @author Zachary Bunyard & Parker Brown
 *
 */
public class Card {

    /**
     * All of the suits
     */
    public enum Suit {
        Club,
        Diamond,
        Heart,
        Spade
    }

    /**
     * All of the faces
     */
    public enum Face {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
    }

    private final Suit SUIT;
    private final Face FACE;

    public Card(Suit SUIT, Face FACE) {
        this.SUIT = SUIT;
        this.FACE = FACE;
    }

    public Suit getSuit() {
        return SUIT;
    }

    public Face getFace() {
        return FACE;
    }

    @Override
    public String toString() {
        // return FACE.toString() + " " + SUIT.toString(); // NEVERMIND JK YOLO
        String str = "";
        switch (SUIT) {
            case Club:
                str = "C";
                break;
            case Diamond:
                str = "D";
                break;
            case Heart:
                str = "H";
                break;
            case Spade:
                str = "S";
                break;
            default:
                break;
        }
        switch (FACE) {
            case Two:
                return 2 + str;
            case Three:
                return 3 + str;
            case Four:
                return 4 + str;
            case Five:
                return 5 + str;
            case Six:
                return 6 + str;
            case Seven:
                return 7 + str;
            case Eight:
                return 8 + str;
            case Nine:
                return 9 + str;
            case Ten:
                return 10 + str;
            case Jack:
                return "J" + str;
            case Queen:
                return "Q" + str;
            case King:
                return "K" + str;
            case Ace:
                return "A" + str;
            default:
                return str;
        }
    }
}
