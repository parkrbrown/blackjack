/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a player. Depending on the type of Player this class is
 * constructed with, it should behave accordingly.
 *
 * @author parkerbrown
 */
public class Player {

    private Scanner scanner;
    private PlayerType playerType;
    private String name;
    private Random random;
    private int wins, loses, ties;
    private double count;
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

    public int getTies() {
        return ties;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the player type
     *
     * @return
     */
    public String getPlayerTypeName() {
        switch (playerType) {
//            case HOUSE:
//                return "HOUSE";
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

    public Player(String name, PlayerType playerType, Scanner scanner) {
        this.name = name;
        this.playerType = playerType;
        this.scanner = scanner;
        this.random = new Random();
        this.wins = 0;
        this.loses = 0;
        this.ties = 0;
        this.count = 0;
    }

    /**
     * This method indicates how many sticks the player would like to take
     *
     * @param sticksLeft
     * @return
     */
    public Card takeHit() { // takeHit
        Deck deck = new Deck();
        Card card = deck.deal();
        return card;
    }

    public int randomPlayer() {
        int choice = 1;

        Random rand = new Random();

        choice = rand.nextInt(2);
        if (choice == 0) {
            takeHit();
        }
        return choice;

    }

    public int smartPlayer1(int visibleCard, int playerHand) {
        int choice = 1;
        if (playerHand < 12) {
            choice = 0; // hit
        } if (visibleCard > 7 && playerHand < 17) {
            choice = 0; // hit 
        }
        return choice;
    }

    public int smartPlayer2(int visibleCard, int[] playerHand) {
        int choice = -1, cardValue = 100, handValue = 0;

        for (int i = 0; i < playerHand.length + 1; i++) {
            if (i >= playerHand.length) {
                cardValue = visibleCard;
            } else {
                cardValue = playerHand[i];
            }
            System.out.println(cardValue);

            handValue = handValue + cardValue;
            if (cardValue == 11 || cardValue == 1) {
                count--;
            } else if (cardValue < 7) {
                count++;
            } else if (cardValue == 10) {
                count--;
            }
            System.out.println("Count after card " + i);
            System.out.println(count);
        }
        System.out.println(count);
        System.exit(0);
        if (handValue <= 11) {
            choice = 0;
        } else if (handValue >= 12 && handValue <= 16) {
            if (count == 0 || count < 0) {
                choice = 0;
            } else if (count > 0) {
                choice = 1;
            }
        } else if (handValue > 16 && handValue < 18) {
            if (count > 0) {
                choice = 1;
            } else if (count <= 0) {
                choice = 0;
            }
        } else if (handValue >= 18) {
            if (count >= 0) {
                choice = 1;
            } else if (count < 0) {
                choice = 0;
            }
        }

        if (shouldShowGameplay) {
            if (choice == 0) {
                System.out.println(name + " chose to hit.");
            } else {
                System.out.println(name + " chose to stay.");
            }
        }
        return choice;
    }

//    private int randomPlayer() {
//        int sticks = random.nextInt(3) + 1;
//        if (shouldShowGameplay) {
//            System.out.println(name + " took " + convertIntToStringName(sticks) + (sticks == 1 ? " matchstick" : " matchsticks") + ".");
//        }
//        return sticks;
//    }
    public int userPlayer() {
        String move = "";
        int choice = -1;
        boolean validInput = false;
        while (validInput == false) {
            System.out.println(name + " would you like to hit or stay?");
            move = scanner.next();
            if (move.equalsIgnoreCase("Hit") || move.equalsIgnoreCase("H")) {
                move = "hit.";
                choice = 0;
                validInput = true;
            } else if (move.equalsIgnoreCase("Stay") || move.equalsIgnoreCase("S")) {
                move = "stay.";
                choice = 1;
                validInput = true;
            } else {
                System.out.print("Please enter a valid move. ");
            }
        }
        System.out.println("Okay, " + move);
        return choice;
    }

    /**
     * This method tells the player that they won
     */
    public void sayYouWin() {
        wins++;
        if (shouldShowGameplay) {
            System.out.println(name + " wins!");
        }
    }

    /**
     * This method tells the player that they lost
     */
    public void sayYouLose() {
        loses++;
        if (shouldShowGameplay) {
            System.out.println(name + " lost!");
        }
    }

    public void sayYouTie() {
        ties++;
        if (shouldShowGameplay) {
            System.out.println(name + " tied with the House!");
        }
    }
}
