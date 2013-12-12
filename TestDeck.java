package blackjack;

import java.util.Scanner;

public class TestDeck
{
   public static void main(String[] args)
   {
      Deck deck = new Deck();
      System.out.println(deck);

      System.out.println("Shuffle cards....");
      deck.shuffle(1000);
      System.out.println(deck);

      Card b;
      int value;
      value = deck.dealValue();
      System.out.println(value);
      b = deck.deal();
      System.out.println("Deal a card: " + b);
      value = deck.dealValue();
      System.out.println(value);
      b = deck.deal();
      System.out.println("Deal a card: " + b);
      value = deck.dealValue();
      System.out.println(value);
      b = deck.deal();
      System.out.println("Deal a card: " + b);
      value = deck.dealValue();
      System.out.println(value);
      b = deck.deal();
      System.out.println("Deal a card: " + b);
      value = deck.dealValue();
      System.out.println(value);
      b = deck.deal();
      System.out.println("Deal a card: " + b);      
      
      System.out.println();
      System.out.println(deck);

   }
}