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
    ArrayList houseHandValue = new ArrayList();
    ArrayList playerHand = new ArrayList();
    ArrayList playerHandValue = new ArrayList();
    
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
        System.out.println(deck);
        deck.shuffle(1000);     // shuffle deck
        System.out.println(deck);

        // This deals one card to each player, twice.
        playerHandValue.add(deck.dealValue());
        playerHand.add(deck.deal());
        houseHandValue.add(deck.dealValue());
        houseHand.add(deck.deal());
        playerHandValue.add(deck.dealValue());
        playerHand.add(deck.deal());
        houseHandValue.add(deck.dealValue());
        houseHand.add(deck.deal());
        
        System.out.println(playerHand);
        System.out.println(playerHandValue);
        System.out.println(houseHand);
        System.out.println(houseHandValue);

       	//System.out.println(playerHand.get(0)); // this is how to get the first card in the playerHand
       	int[] playerHandValueArray = {6,5};  // TODO: Convert arraylist playerHandValue to array
       	int[] houseHandValueArray = {7, 10}; // TODO: Convert arraylist houseHandValue to array
       	
        int handValue = playerHandValueArray[0] + playerHandValueArray[1];
        int houseHandValue = houseHandValueArray[0] + houseHandValueArray[1];
        
        System.out.println(handValue);
        System.out.println(houseHandValue);
        System.exit(0);
        
        //Checks if house or player have blackjack and if not continues game. Otherwise, declares winner.
        if (houseHandValue == 21 && (houseHand.get(0) == Card.getRank()[11])) { // TODO: Does this work? Make it work for Jack, Kind, and Queen (11, 12, 13)
        	//sayYouLose();		// TODO: Call from Player class
        } else if (handValue == 21 && playerHand.get(0) == Card.getRank()[11]) { // TODO: Does this work? Make it work for Jack, Kind, and Queen (11, 12, 13)
        	//sayYouWin(); // TODO: Call from Player class
        } else {
        	int number = 0;
        	boolean endOfTurn = false;
        
        	//Loops for as many times as player hits unless player stays, reaches 21, or busts
        	while (endOfTurn == false) {
        		// TODO: Go to player logic that will return int...
        		//if (playerType == RANDOM) {
        			//randomPlayer(); // TODO: Call from Player class
        		//} else if (playerType == SMART) {
        			//smartPlayer(); // TODO: Call from Player class
        		//} else {
        			//userPlayer(); // TODO: Call from Player class
        		//}
        		if (number == 0) {
        			playerHand.add(deck.deal());
        		} else {
        			endOfTurn = true;
        		}
        		if (handValue > 21) {
        			System.out.println("Bust");
        			//sayYouLoser(); // TODO: Call from Player class
        			endOfTurn = true;
        		} else if (handValue == 21) {
        			endOfTurn = true;
        		}
        	}
        	endOfTurn = false;
        	while (endOfTurn == false) {
        		if (houseHandValue < 17) {
        			houseHand.add(deck.deal());
        		} else if (houseHandValue > 21) {
        			System.out.println("Bust");
        			//sayYouWin(); // TODO: Call from Player class
        			endOfTurn = true;
        		} else {
        			endOfTurn = true;
        		}
        	}
        	if (handValue > houseHandValue && handValue <= 21) {
        		System.out.println("Player wins!");
        		// sayYouWin(); // TODO: Call from Player class
        	} else if (handValue < houseHandValue && houseHandValue <= 21) {
        		System.out.println("House wins!");
        		//sayYouLose(); // TODO: Call from Player class
        	}
       
        	// TODO: compare values. Closest to 21 wins.
        }
            
// TODO: Do we even need this?
//        while (true) {
//            if (shouldShowGameplay()) {
//                //printHand();
//            }
////            sticksLeft -= currentTurn.takeSticks(sticksLeft);
//
//            if (sticksLeft <= 0) {
//                currentTurn.sayYouLose();
//                switchTurns();
//                currentTurn.sayYouWin();
//            }
//            switchTurns();
//        }
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
        
        double winPercent = (100 * ((PLAYER.getWins() / (double)NUMBER_OF_GAMES) + 0.0005));
        double lossPercent = (100 * ((PLAYER.getLoses() / (double)NUMBER_OF_GAMES) + 0.0005));
        System.out.println("Player One wins: " + winPercent + "%");
        System.out.println("Player One losses: " + lossPercent + "%");
    }

// TODO: Is this needed?
//    private void switchTurns() {
//        if (currentTurn == PLAYER) {
//            currentTurn = PLAYER;
//        } else {
//            currentTurn = PLAYER;
//        }
//    }
}
