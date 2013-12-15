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
        }
        if (houseHandValueInt > 21) {
            houseHandValueInt -= 10;
        }

        if (shouldShowGameplay()) {
            System.out.println("Your hand: " + playerHand);
            System.out.println("House visible card: " + houseHand.get(1)); // show house 2nd card
        }

        boolean noOneWonYet = true;
        // Determine if either House or player have Blackjack hand
        String[] array = Card.getRank();
        String[] array2 = Card.getSuit();
        //Card[] cardFace;
        String cardFace;
        int i, j;
        
        if (houseHandValueInt == 21 || handValueInt == 21) {
        	for (i = 1; i < 5; i++) {
        		for (j = 11; j < 14; j++) {
        			cardFace = array[j] + array2[i];
        			if (houseHand.get(0).toString().equals(cardFace) || houseHand.get(1).toString().equals(cardFace)) {
        				if (shouldShowGameplay()) {
        					System.out.println("House has Blackjack!");
        				}
        				noOneWonYet = false;
        				PLAYER.sayYouLostToBlackjack();
        				PLAYER.sayYouLose();
        			} else if (playerHand.get(0).toString().equals(cardFace) || playerHand.get(1).toString().equals(cardFace)) {
        				if (shouldShowGameplay()) {
        					System.out.println("Player has Blackjack!");
        				}
        				noOneWonYet = false;
        				PLAYER.sayYouGotBlackjack();
        				PLAYER.sayYouWin();
        			}
        		}
        	}
        }        
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
            System.out.println("Okay, " + PLAYER.getPlayerTypeName() + " and the House just finished playing " + intToString(NUMBER_OF_GAMES) + " games.  Here is the outcome:");
        }
        System.out.println(PLAYER.getPlayerTypeName() + " wins: " + PLAYER.getWins());
        System.out.println("House wins: " + PLAYER.getLoses());
        System.out.println(PLAYER.getPlayerTypeName() + " Blackjack wins: " + PLAYER.getPlayerBlackjacks());
        System.out.println("House Blackjack wins: " + PLAYER.getHouseBlackjacks());

        DecimalFormat fmt = new DecimalFormat("0.00#");
        double winPercent = (100 * ((PLAYER.getWins() / (double) NUMBER_OF_GAMES)));// + 0.0005));
        double lossPercent = (100 * ((PLAYER.getLoses() / (double) NUMBER_OF_GAMES)));// + 0.0005));
        double playerBlackjackPercent = (100 * ((PLAYER.getPlayerBlackjacks() / (double) NUMBER_OF_GAMES)));
        double houseBlackjackPercent = (100 * ((PLAYER.getHouseBlackjacks() / (double) NUMBER_OF_GAMES)));
        System.out.println(PLAYER.getPlayerTypeName() + " wins: " + fmt.format(winPercent) + "%");
        System.out.println("House wins: " + fmt.format(lossPercent) + "%");
        System.out.println(PLAYER.getPlayerTypeName() + " got Blackjack: " + fmt.format(playerBlackjackPercent) + "%");
        System.out.println("House got blackjack: " + fmt.format(houseBlackjackPercent) + "%");
    }
    
    private String intToString (int numberOfPlayers) {
	 	String numberInWord = "";
	 	switch (numberOfPlayers) {
	 	case 1:
		 	numberInWord = "one";
		 	break;
	 	case 2:
		 	numberInWord = "two";
		 	break;
	 	case 3:
		 	numberInWord = "three";
		 	break;
	 	case 4:
		 	numberInWord = "four";
		 	break;
	 	case 5:
		 	numberInWord = "five";
		 	break;
	 	case 6:
		 	numberInWord = "six";
		 	break;
	 	case 7:
		 	numberInWord = "seven";
		 	break;
	 	case 8:
		 	numberInWord = "eight";
		 	break;
	 	case 9:
		 	numberInWord = "nine";
		 	break;
	 	case 10:
		 	numberInWord = "ten";
		 	break;
		 	default:
		 		numberInWord += numberOfPlayers;
	 	}
	 	return numberInWord;
 	}
}
