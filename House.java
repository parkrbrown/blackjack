/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class is for coordinating with the players (e.g., finding out what move
 * the current player would like to make), declaring the winner, etc.
 *
 * @author parkerbrown & Zach Bunyard
 */
public class House {

    private final Player PLAYER;
    // private int sticksLeft;
    private final int NUMBER_OF_GAMES;
    // private final int NUMBER_OF_STICKS;
    //private final int MAX_STICKS_THAT_CAN_BE_TAKEN;

    private Player currentTurn;
    
    private int gamesPlayed = 0;

//    ArrayList houseHand = new ArrayList();
//    ArrayList houseHandValue = new ArrayList();
//    ArrayList playerHand = new ArrayList();
//    ArrayList playerHandValue = new ArrayList();
    private Card rank;

    private boolean shouldShowGameplay() {
    	if (PLAYER.getPlayerType() == PlayerType.USER) {
    		return true;
    	} else {
    		return false;
    	}
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
        
        boolean typeOfPlayer = shouldShowGameplay();
        boolean playerHasTwoAces = false, houseHasTwoAces = false;

        if (shouldShowGameplay()) {
            if (gamesPlayed == 0){
                System.out.println("Okay, " + PLAYER.getPlayerTypeName());
            } else {
                System.out.println(); // line break for new game
                System.out.println("Here is your new hand: ");
            }
            
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
        
      //System.out.println(playerHand.get(0)); // this is how to get the first card in the playerHand
        int[] playerHandValueArray = convertToArray(playerHandValue);
        int[] houseHandValueArray = convertToArray(houseHandValue);

        int handValueInt = playerHandValueArray[0] + playerHandValueArray[1];
        int houseHandValueInt = houseHandValueArray[0] + houseHandValueArray[1];

        if (handValueInt > 21) {
            handValueInt -= 10;
            playerHasTwoAces = true;
        }
        if (houseHandValueInt > 21) {
            houseHandValueInt -= 10;
            houseHasTwoAces = true;
        }
        
        //Checks if house or player have blackjack and if not continues game. Otherwise, declares winner.
        System.out.println("House: " + houseHandValueInt);
        System.out.println("House: " + houseHand);
        System.out.println("Player: " + handValueInt);
        System.out.println("Player: " + playerHand);

        if (shouldShowGameplay()) {
            System.out.println("Your hand: " + playerHand);
            System.out.println("House visible card: " + houseHand.get(1)); // show house 2nd card
        }

        boolean noOneWonYet = true;
        // Determine if either House or player have Blackjack hand TODO: Get blackjack checker working
        String[] array = Card.getRank();
        String[] array2 = Card.getSuit();
        //Card[] cardFace;
        String cardFace;
        int i, j;
        
        if (houseHandValueInt == 21 || handValueInt == 21) {
        	for (i = 1; i < 5; i++) {
        		for (j = 11; j < 14; j++) {
        			cardFace = array[j] + array2[i];
        			System.out.println(cardFace); // Works up to this point TODO: Remove after testing
        			System.out.println(houseHand); // TODO: Remove after testing
        			System.out.println(playerHand); // TODO: Remove after testing
        			
        			
        			//If statement doesn't show any conditions to be true, even if they are.
        			
        			
        			if (houseHand.get(0) == cardFace || houseHand.get(1) == cardFace) { //Seems to be a break point
        				System.out.println("House has Blackjack!");
        				noOneWonYet = false;
        				PLAYER.sayYouLose();
        			} else if (playerHand.get(0) == cardFace || playerHand.get(1) == cardFace) { //Also part of break point
        				System.out.println("Player has Blackjack!");
        				noOneWonYet = false;
        				PLAYER.sayYouWin();
        			}
        		}
        	}
        }
        
        System.exit(0); // TODO: Remove after blackjack tester is working.
        
        //Only starts if neither player nor house have blackjack
        while (noOneWonYet == true) {
            int choice = 0;
            boolean endOfTurn = false;
            boolean playerBust = false, houseBust = false;

            //Loops for as many times as player hits unless player stays, reaches 21, or busts
            while (endOfTurn == false) {
                //Determines what logic to base decision to hit or stay off of.
                PlayerType playerType = PLAYER.getPlayerType();
                if (playerType == PlayerType.RANDOM) {
                    choice = PLAYER.randomPlayer();
                } else if (playerType == PlayerType.SMART) {
                	choice = PLAYER.smartPlayer1(houseHandValueArray[1], handValueInt);
                } else {
                	System.out.println(playerHand);
                    choice = PLAYER.userPlayer();
                }

                if (choice == 0) {
                    playerHandValue.add(deck.dealValue());
                    playerHand.add(deck.deal());
                    playerHandValueArray = convertToArray(playerHandValue);
                    handValueInt += playerHandValueArray[playerHandValue.size() - 1];
                    if (playerHasTwoAces == true) {
                    	handValueInt -= 10;
                    }
                } else if (choice == 1) {
                    endOfTurn = true;
                } else {
                    System.err.println("ERROR MAKING CHOICE");
                    System.exit(0);
                }

                if (handValueInt > 21) {
                	if (shouldShowGameplay()) {
                		System.out.println("Player bust");
                	}
                    playerBust = true;
                    endOfTurn = true;
                } else if (handValueInt == 21) {
                    endOfTurn = true;
                }
            }
            endOfTurn = false;
            if (shouldShowGameplay()) {
        		System.out.println("House hand: " + houseHand);
        	}
            while (endOfTurn == false) {
                if (houseHandValueInt < 17) {
                    houseHandValue.add(deck.dealValue());
                    houseHand.add(deck.deal());
                    if (shouldShowGameplay()) {
                    	System.out.println("House hits: " + houseHand);
                    }
                houseHandValueArray = convertToArray(houseHandValue);
                houseHandValueInt += houseHandValueArray[houseHand.size() - 1];
                if (houseHasTwoAces == true) {
                	houseHandValueInt -= 10;
                }
                } else if (houseHandValueInt > 21) {
                	if (shouldShowGameplay()) {
                		System.out.println("House bust");
                	}
                    houseBust = true;
                    endOfTurn = true;
                } else {
                	if (shouldShowGameplay()) {
                		System.out.println("House stays.");
                	}
                    endOfTurn = true;
                }
            }
            if ((playerBust == false && houseBust == true) || (handValueInt > houseHandValueInt && handValueInt <= 21)) {
                PLAYER.sayYouWin();
            } else if ((playerBust == true && houseBust == false) || (houseHandValueInt > handValueInt && houseHandValueInt <= 21) || (playerBust == true && houseBust == true) || (handValueInt == houseHandValueInt)) {
                PLAYER.sayYouLose();
            }
            noOneWonYet = false;
        }
        gamesPlayed++;
    }

    //Converts ArrayList of ints to int[] array
    public int[] convertToArray(ArrayList arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0, size = arrayList.size(); i < size; i++) {
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
        double winPercent = (100 * ((PLAYER.getWins() / (double) NUMBER_OF_GAMES)));// + 0.0005));
        double lossPercent = (100 * ((PLAYER.getLoses() / (double) NUMBER_OF_GAMES)));// + 0.0005));
        double tiePercent = (100 * ((PLAYER.getTies() / (double) NUMBER_OF_GAMES)));
        System.out.println("Player One wins: " + fmt.format(winPercent) + "%");
        System.out.println("Player One losses: " + fmt.format(lossPercent) + "%");
        System.out.println("Ties: " + fmt.format(tiePercent) + "%");
    }
}
