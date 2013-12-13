/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * This class is for coordinating with the players (e.g., finding out what move the current player would like to make), declaring the winner, etc.
 * @author parkerbrown
 */
public class House {

    private final Player PLAYER;
   // private int sticksLeft;
    private final int NUMBER_OF_GAMES;
   // private final int NUMBER_OF_STICKS;
    //private final int MAX_STICKS_THAT_CAN_BE_TAKEN;

    private Player currentTurn;
    
//    ArrayList houseHand = new ArrayList();
//    ArrayList houseHandValue = new ArrayList();
//    ArrayList playerHand = new ArrayList();
//    ArrayList playerHandValue = new ArrayList();
    
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
        //this.NUMBER_OF_STICKS = 15;
        //this.MAX_STICKS_THAT_CAN_BE_TAKEN = 3; // This is a stretch goal
    }

    /**
     * Prints out sticks
     */
//    public void printSticks() {
//        for (int i = 0; i < sticksLeft; i++) {
//            System.out.print("|");
//        }
//        System.out.println(); // line break
//    }

    /**
     * Plays the game
     */
    public void play() {    
    	ArrayList houseHand = new ArrayList();
        ArrayList houseHandValue = new ArrayList();
        ArrayList playerHand = new ArrayList();
        ArrayList playerHandValue = new ArrayList();
    	
        if (shouldShowGameplay()) {
            System.out.println("Okay, " + PLAYER.getPlayerTypeName()); // 
        }
        
        Deck deck = new Deck();   // get deck
        deck.shuffle(1000);     // shuffle deck

        // This deals one card to each player, twice.
        playerHandValue.add(deck.dealValue());
        playerHand.add(deck.deal());
        houseHandValue.add(deck.dealValue());
        houseHand.add(deck.deal());
        playerHandValue.add(deck.dealValue());
        playerHand.add(deck.deal());
        houseHandValue.add(deck.dealValue());
        houseHand.add(deck.deal());
        
//        System.out.println(playerHand);
//        System.out.println(playerHandValue);
//        System.out.println(houseHand);
//        System.out.println(houseHandValue);

       	//System.out.println(playerHand.get(0)); // this is how to get the first card in the playerHand
       
        int[] playerHandValueArray = convertToArray(playerHandValue);
        int[] houseHandValueArray = convertToArray(houseHandValue);
       	
        int handValueInt = playerHandValueArray[0] + playerHandValueArray[1];
        int houseHandValueInt = houseHandValueArray[0] + houseHandValueArray[1];
        
//        System.out.println(handValueInt);
//        System.out.println(houseHandValue);
        
        //Checks if house or player have blackjack and if not continues game. Otherwise, declares winner.
                
        String[] array = Card.getRank();
        String[] array2 = Card.getSuit();
        boolean noOneWonYet = true;
        
        for (int i = 1; i < 4; i++) {
        	for (int j = 11; j < 13; j++) {
        		String cardFace = array[j] + array2[i];
        		if (houseHandValueInt == 21 && (houseHand.get(0) == cardFace || houseHand.get(1) == cardFace)) {
        	        System.out.println("House has Blackjack!");
        	       	PLAYER.sayYouLose();
        	       	noOneWonYet = false;
        	    } else if (handValueInt == 21 && (playerHand.get(0) == cardFace || playerHand.get(1) == cardFace)) {
        	       	System.out.println("Player has Blackjack!");
        	       	PLAYER.sayYouWin();
        	       	noOneWonYet = false;
        	    }
        	}
        }
        //Only starts if neither player nor house have blackjack
        while (noOneWonYet == true) {
        	int choice = 0, cardCount = 0;
        	boolean endOfTurn = false;
        	boolean playerBust = false, houseBust = false;
        
        	//Loops for as many times as player hits unless player stays, reaches 21, or busts
        	while (endOfTurn == false) {
        		//Determines what logic to base decision to hit or stay off of.
        		PlayerType playerType = PLAYER.getPlayerType();
        		if (playerType == PlayerType.RANDOM) {
        			choice = PLAYER.randomPlayer();
        		} else if (playerType == PlayerType.SMART) {
        			choice = PLAYER.smartPlayer2(houseHandValueArray[1], playerHandValueArray, cardCount); // TODO: Figure out how to keep track of count inside and out of class
        		} else {
        			choice = PLAYER.userPlayer();
        		}
        		
        		if (choice == 0) {
        			playerHandValue.add(deck.dealValue());
        			playerHand.add(deck.deal());
        			System.out.println(playerHand);
        			playerHandValueArray = convertToArray(playerHandValue);
        			handValueInt = 0;
            		for (int i = 0; i < playerHand.size(); i++) {
            			handValueInt = handValueInt + playerHandValueArray[i];
            		}
        		} else if (choice == 1) {
        			endOfTurn = true;
        		} else {
        			System.err.println("ERROR MAKING CHOICE");
        			System.exit(0);
        		}
        		
        		if (handValueInt > 21) {
        			System.out.println("Player bust");
        			playerBust = true;
        			endOfTurn = true;
        		} else if (handValueInt == 21) {
        			endOfTurn = true;
        		}
        	}
        	endOfTurn = false;
        	while (endOfTurn == false) {
        		if (houseHandValueInt < 17) {
        			System.out.println(houseHandValue);
        			houseHandValue.add(deck.dealValue());
        			houseHand.add(deck.deal());
        			houseHandValueArray = convertToArray(houseHandValue);
        			houseHandValueInt = 0;
            		for (int i = 0; i < playerHand.size(); i++) {
            			houseHandValueInt = houseHandValueInt + playerHandValueArray[i];
            		}
        		} else if (houseHandValueInt > 21) {
        			System.out.println("House bust");
        			houseBust = true;
        			endOfTurn = true;
        		} else {
        			endOfTurn = true;
        		}
        	}
        	if ((playerBust == false && houseBust == true) || (handValueInt > houseHandValueInt && handValueInt <= 21)) {
        		System.out.println("Player wins!");
        		PLAYER.sayYouWin();
        	} else if ((playerBust == true && houseBust == false) || (houseHandValueInt > handValueInt && houseHandValueInt <= 21) || (playerBust == true && houseBust == true)) {
        		System.out.println("House wins!");
        		PLAYER.sayYouLose();
        	} else if (handValueInt == houseHandValueInt || (handValueInt > 21 && houseHandValueInt > 21)) {
        		System.out.println("Tie!");
        		PLAYER.sayYouTie();
        	}      
        	noOneWonYet = false;
        }
    }    
    
    //Converts ArrayList of ints to int[] array
    public int[] convertToArray( ArrayList arrayList ) {
        int [] array = new int[arrayList.size()];
       	for(int i = 0, size = arrayList.size(); i < size; i++) {
       	   array[i] = (int) arrayList.get(i);
       	}   
       	return array;
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
        System.out.println("Ties: " + PLAYER.getTies());
        
        DecimalFormat fmt = new DecimalFormat("0.00#");
        double winPercent = (100 * ((PLAYER.getWins() / (double)NUMBER_OF_GAMES)));// + 0.0005));
        double lossPercent = (100 * ((PLAYER.getLoses() / (double)NUMBER_OF_GAMES)));// + 0.0005));
        double tiePercent = (100 * ((PLAYER.getTies() / (double)NUMBER_OF_GAMES)));
        System.out.println("Player One wins: " + fmt.format(winPercent) + "%");
        System.out.println("Player One losses: " + fmt.format(lossPercent) + "%");
        System.out.println("Ties: " + fmt.format(tiePercent) + "%");
    }
}
