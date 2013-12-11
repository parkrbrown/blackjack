package blackjack;

public class TestDeck
{
   public static void main(String[] args)
   {
      DeckOfCards a;

      a = new DeckOfCards();
      System.out.println(a);

      System.out.println("Shuffle cards....");
      a.shuffle(1000);
      System.out.println(a);

      Card b;

      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);

   }
}