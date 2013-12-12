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
   // private int sticksLeft;
    private final int NUMBER_OF_GAMES;
   // private final int NUMBER_OF_STICKS;
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
       
        int[] playerHandValueArray = convertToArray(playerHandValue);
        int[] houseHandValueArray = convertToArray(houseHandValue);
//       	int [] playerHandValueArray = new int[playerHandValue.size()];
//       	for(int i=0, len = playerHandValue.size(); i < len; i++) {
//       	   playerHandValueArray[i] = (int) playerHandValue.get(i);
//       	}
//       	
//       	int [] houseHandValueArray = new int[houseHandValue.size()];
//       	for(int i=0, len = houseHandValue.size(); i < len; i++) {
//       	   houseHandValueArray[i] = (int) houseHandValue.get(i);
//       	}
//       	System.out.println(playerHandValueArray);
//       	System.out.println(houseHandValueArray);
       	
        int handValue = 21; // playerHandValueArray[0] + playerHandValueArray[1];
        int houseHandValue = 21; //houseHandValueArray[0] + houseHandValueArray[1];
        
        System.out.println(handValue);
        System.out.println(houseHandValue);
        
        //Checks if house or player have blackjack and if not continues game. Otherwise, declares winner.
        
        Card card = new Card();
        
        String[] array = card.getRank();
        System.out.println(houseHand.get(0));
        Deck deck2 = new Deck();
        System.out.println(deck2);
        
        if (houseHandValue == 21 && (houseHand.get(0) == array[11] || houseHand.get(1) == array[11] 
        		|| houseHand.get(0) == array[12] || houseHand.get(1) == array[12]
        				|| houseHand.get(0) == array[13] || houseHand.get(1) == array[13])) {
        	PLAYER.sayYouLose();
        } else if (handValue == 21 && (playerHand.get(0) == array[11] || playerHand.get(1) == array[11] 
        		|| playerHand.get(0) == array[12] || playerHand.get(1) == array[12]
        				|| playerHand.get(0) == array[13] || playerHand.get(1) == array[13])) {
        	PLAYER.sayYouWin();
        } else {
System.out.println("BLAH");
System.exit(0);
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
    			playerHandValueArray = convertToArray(playerHand);
    			handValue = 0;
        		for (int i = 0; i < playerHand.size(); i++) {
        			handValue = handValue + playerHandValueArray[i];
        		}
        		// TODO: Update value of player hand
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
        		// TODO: Update value of house hand
        	}
        	if (handValue > houseHandValue && handValue <= 21) {
        		System.out.println("Player wins!");
        		// sayYouWin(); // TODO: Call from Player class
        	} else if (handValue < houseHandValue && houseHandValue <= 21) {
        		System.out.println("House wins!");
        		//sayYouLose(); // TODO: Call from Player class
        	} else if (handValue == houseHandValue) {
        		System.out.println("Tie!");
        		//sayYouTie(); // TODO: Create and call from Player class
        	}       
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
    
    //Converts ArrayList of ints to int[] array
    public int[] convertToArray( ArrayList arrayList ) {
        int [] array = new int[arrayList.size()];
       	for(int i=0, size = arrayList.size(); i < size; i++) {
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
