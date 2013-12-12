package blackjack;

public class TestDeck
{
   public static void main(String[] args)
   {
      Deck a;

      a = new Deck();
      System.out.println(a);

      System.out.println("Shuffle cards....");
      a.shuffle(1000);
      System.out.println(a);

      Card b;

      b = a.deal();
      System.out.println("Deal a card: " + b);
      Scanner input = new Scanner(System.in);
      System.out.println();
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      b = a.deal();
      System.out.println("Deal a card: " + b);
      
      
      System.out.println();
      System.out.println(a);

   }
}