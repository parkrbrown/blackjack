/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 * This class represents a player. Depending on the type of Player this class is
 * constructed with, it should behave accordingly.
 *
 * @author parkerbrown & Zach Bunyard
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private Scanner scanner;
    private Random random;
    private final ArrayList<Card> CARDS;
    private final PlayerType PLAYER_TYPE;
    private final String NAME;
    private int wins, loses;
    private boolean shouldShowGameplay;

    public void setShouldShowGameplay(boolean value) {
        shouldShowGameplay = value;
    }

    public boolean getshouldShowGameplay() {
        return shouldShowGameplay;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public PlayerType getPlayerType() {
        return PLAYER_TYPE;
    }

    public String getName() {
        return NAME;
    }

    public int getHandValue() {
        return Game.calculateHandValue(CARDS);
    }

    public boolean hasBlackjack() {
        return CARDS.size() == 2 && Game.calculateHandValue(CARDS) == 21;
    }

    public boolean hasBusted() {
        return Game.calculateHandValue(CARDS) > 21;
    }

    public void discardHand() {
        CARDS.clear();
    }

    public Card getCard(int index) {
        return CARDS.get(index);
    }

    /**
     * Gets the player type
     *
     * @return
     */
    public String getPlayerTypeName() {
        switch (PLAYER_TYPE) {
            case RANDOM:
                return "RANDOM";
            case SMART:
                return "SMART";
            case USER:
                return "USER";
            default:
                return "";
        }
    }

    /**
     *
     * @param NAME
     * @param PLAYER_TYPE
     * @param scanner
     */
    public Player(String NAME, PlayerType PLAYER_TYPE, Scanner scanner) {
        this.NAME = NAME;
        this.PLAYER_TYPE = PLAYER_TYPE;
        this.scanner = scanner;
        this.random = new Random();
        this.wins = 0;
        this.loses = 0;
        this.CARDS = new ArrayList<>();
    }

    /**
     * Take hit
     * @param deck
     */
    public void takeCard(Deck deck) {
        CARDS.add(deck.draw());
    }

    /**
     * Random Player
     * @param deck
     * @return
     */
    public boolean randomPlayer(Deck deck) {

        boolean shouldHit = random.nextInt(2) == 1;
        if (shouldHit) {
            takeCard(deck);
        }
        return shouldHit;

    }

    /**
     * Smart Player
     * @param deck
     * @param dealerVisibleValue
     * @return
     */
    public boolean smartPlayer(Deck deck, int dealerVisibleValue) {
        boolean shouldHit = false;

        if (getHandValue() < 12) {
            shouldHit = true;
        }
        if (dealerVisibleValue > 6 && getHandValue() < 17) { // turns out 6 is better than 7
            shouldHit = true;
        }

        if (shouldHit) {
            takeCard(deck);
        }

        return shouldHit;
    }

    /**
     * User Player
     * @param deck
     * @return
     */
    public boolean userPlayer(Deck deck, Card dealerSecondCard) {
        String move = "";
        boolean shouldHit = false;
        boolean validInput = false;
        while (validInput == false) {
            System.out.println("Your hand: " + getDisplayCards() + "- " + getHandValue());
            System.out.println("House's visible card: " + dealerSecondCard + " - " + Game.calculateCardValue(dealerSecondCard));
            System.out.println(NAME + " would you like to hit or stay?");
            move = scanner.next();
            if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("H")) {
                move = "hit.";
                shouldHit = true;
                validInput = true;
            } else if (move.equalsIgnoreCase("Stay") || move.equalsIgnoreCase("S")) {
                move = "stay.";
                validInput = true;
            } else {
                System.out.print("Please enter a valid move. ");
            }
        }
        System.out.println("Okay, " + move);

        if (shouldHit) {
            takeCard(deck);
        }

        return shouldHit;
    }
    
    public String getDisplayCards(){
        String str = "";
        for(Card card : CARDS){
            str+= (card + " ");
        }
        return str;
    }

    /**
     * This method tells the player that they won and adds a win to the win count
     */
    public void sayYouWin() {
        wins++;
        if (shouldShowGameplay) {
            System.out.println(NAME + " wins!");
        }
    }

    /**
     * This method tells the player that they lost and adds a loss to the loss count
     */
    public void sayYouLose() {
        loses++;
        if (shouldShowGameplay) {
            System.out.println("House wins!");
        }
    }
}
