/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

/**
 * This class is for coordinating with the players (e.g., finding out what move the current player would like to make), declaring the winner, etc.
 * @author parkerbrown
 */
public class House {

    private final Player PLAYER;
    private int sticksLeft;
    private final int NUMBER_OF_GAMES;
    private final int NUMBER_OF_STICKS;
    //private final int MAX_STICKS_THAT_CAN_BE_TAKEN;

    private Player currentTurn;
    
    ArrayList houseHand = new ArrayList();
    ArrayList playerHand = new ArrayList();
    
    private Card rank;

    private boolean shouldShowGameplay() {
        return PLAYER.getPlayerType() == PlayerType.USER;
    }

    /**
     *
     * @param player
     * @param numberOfGames
     */
    public House(Player player, int numberOfGames) {
        this.PLAYER = player;
        this.PLAYER.setShouldShowGameplay(shouldShowGameplay());
        this.NUMBER_OF_GAMES = numberOfGames;
        this.NUMBER_OF_STICKS = 15;
        //this.MAX_STICKS_THAT_CAN_BE_TAKEN = 3; // This is a stretch goal
    }

    /**
     * Prints out sticks
     */
    public void printSticks() {
        for (int i = 0; i < sticksLeft; i++) {
            System.out.print("|");
        }
        System.out.println(); // line break
    }

    /**
     * Plays the game
     */
    public void play() {
        if (shouldShowGameplay()) {
            System.out.println("Okay, " + PLAYER.getPlayerTypeName()); // 
        }
        currentTurn = PLAYER;
        
        Deck deck = new Deck();   // get deck
        deck.shuffle(1000);     // shuffle deck
        
        // This deals one card to each player, twice.
        playerHand.add(deck.deal());
        houseHand.add(deck.deal()); 
        playerHand.add(deck.deal());
        houseHand.add(deck.deal()); 
        
        System.out.println(playerHand);
        System.out.println(houseHand);
        
        System.out.println(playerHand.get(0)); // this is how to get the first card in the playerHand
        

        // if house has blackjack
            // they win
        // else if player has blackjack
               // they win
        // else 
            // continue playing
        // wait for player decision
        // if player asks for hit 
            // give one card to playe r- loop until stay or bust
        // bust automatically loses
        // if player stays, house plays
            // if hand value < 17, take hit - loop
            // else stay
       //compare values. Closest to 21 wins.
            

        while (true) {
            if (shouldShowGameplay()) {
                //printHand();
            }
            sticksLeft -= currentTurn.takeSticks(sticksLeft);

            if (sticksLeft <= 0) {
                currentTurn.sayYouLose();
                switchTurns();
                currentTurn.sayYouWin();
            }
            switchTurns();
        }

    }

    /**
     * Prints out results after all games have been played
     */
    public void showResults() {
        if (!shouldShowGameplay()) {
            System.out.println("Okay, Player 1 (" + PLAYER.getPlayerTypeName() + ") just finished playing " + NUMBER_OF_GAMES + " games.  Here is the outcome:");
        }
        System.out.println(PLAYER.getPlayerTypeName() + " wins: " + PLAYER.getWins());
        System.out.println("House wins: " + PLAYER.getLoses());
    }

    private void switchTurns() {
        if (currentTurn == PLAYER) {
            currentTurn = PLAYER;
        } else {
            currentTurn = PLAYER;
        }
    }
}
