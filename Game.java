/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjack;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author parkerbrown
 */
public class Game {
    
    private Player player;
    private Player house;
    
    private int gamesPlayed = 0;
    private Deck deck;
    
    private boolean shouldShowGameplay() {
        return player.getPlayerType() == PlayerType.USER;
    }

    public Game(Player player, Scanner scanner){
        this.player = player;
        this.house = new Player("House", PlayerType.HOUSE, scanner);
    }
    
    public void play(){
    	if (gamesPlayed > 0 && shouldShowGameplay()) {
    		System.out.println("\nNext Game:\n");
    	}
        gamesPlayed++;
        deck = new Deck();
        deck.shuffle(1000);
        
        player.takeCard(deck);
        house.takeCard(deck);
        player.takeCard(deck);
        house.takeCard(deck);
        
        
        while(true){
            // if house has blackjack and player does not --- house wins
            if(house.hasBlackjack() && !player.hasBlackjack()){
                houseWon();
                break;
            }
            // if player has blackjack and house does not --- player wins
            if(!house.hasBlackjack() && player.hasBlackjack()){
                playerWon();
                break;
            }
            // both player and house has blackjack --- tie, do nothing
            if(house.hasBlackjack() && player.hasBlackjack()){
                tiedGame();
                break;
            }
            
            runPlayerRounds();
            runHouseRounds();
            
            //Lets USER type player know if house busted
            if (house.hasBusted() && shouldShowGameplay()) {
            	System.out.println("THE HOUSE BUST!");
            }

            // if the house hasn't busted, and they have a higher value than player (unless player busted) --- house wins
            if(!house.hasBusted() && (house.getHandValue() > player.getHandValue() || player.hasBusted())){
                houseWon();
                break;
            }
            // if the player hasn't busted, and they have a higher value than the house (unless house busted) ---- player wins
            if (!player.hasBusted() && (player.getHandValue() > house.getHandValue() || house.hasBusted())){
                playerWon();
                break;
            }
            // if they both busted or both have the same value
            if((house.hasBusted() && player.hasBusted()) || house.getHandValue() == player.getHandValue()){
                tiedGame();
                break;
            }
        }
        
        player.discardHand();
        house.discardHand();
    }
    
    private void houseWon(){
        house.sayYouWin();
        player.sayYouLose();
        if(shouldShowGameplay()){
            System.out.println("THE HOUSE WON!");
            showPlayerAndHouseCards();
        }
    }
    
    private void playerWon(){
        house.sayYouLose();
        player.sayYouWin();
        if(shouldShowGameplay()){
            System.out.println(player.getPlayerTypeName() + " WON!");
            showPlayerAndHouseCards();
        }
    }
    
    private void tiedGame(){
        if(shouldShowGameplay()){
            System.out.println("PUSH!");
            showPlayerAndHouseCards();
        }
    }
    
    private void showPlayerAndHouseCards(){
        System.out.println("House: " + house.getDisplayCards() + " - " + house.getHandValue());
        System.out.println(player.getPlayerTypeName() + ": " + player.getDisplayCards() + " - " + player.getHandValue());
    }
    
    private void runPlayerRounds(){
        switch(player.getPlayerType()){
            case RANDOM:
                while(player.randomPlayer(deck) && player.hasBusted() == false && player.has21() == false){}
                break;
            case USER:
                while(player.userPlayer(deck, house.getCard(1)) && player.hasBusted() == false && player.has21() == false){}
                if (player.hasBusted() == true) {
                	System.out.println(player.getPlayerTypeName() + " BUST!");
                }
                break;
            case SMART:
                while(player.smartPlayer(deck, calculateCardValue(house.getCard(1)))) {}
                break;
            default:
                break;
        }
        
    }
    
    private void runHouseRounds(){
        while(true){
            if(house.getHandValue() > 16) {
                break;
            }
            house.takeCard(deck);
        }
    }
    
    public void showResults(){
        if(!shouldShowGameplay()){
            System.out.println("Okay, " + player.getPlayerTypeName() + " and the House just finished playing " + gamesPlayed + " games. Here is the outcome:");
        }
        
        int gamesPlayedThatWerentATie = player.getWins() + house.getWins();
        
        System.out.println(player.getPlayerTypeName() + " wins: " + player.getWins());
        System.out.println("House wins: " + player.getLoses());
        System.out.println("Pushes: " + (gamesPlayed - gamesPlayedThatWerentATie));
        
        DecimalFormat fmt = new DecimalFormat("0.00#");
        double winPercent = (100 * ((player.getWins() / (double) gamesPlayed)));
        double lossPercent = (100 * ((player.getLoses() / (double) gamesPlayed)));
        double tiePercent = (100 * (((gamesPlayed - gamesPlayedThatWerentATie) / (double) gamesPlayed)));
        System.out.println(player.getPlayerTypeName() + " wins: " + fmt.format(winPercent) + "%");
        System.out.println("House wins: " + fmt.format(lossPercent) + "%");
        System.out.println("Pushes occured: " + fmt.format(tiePercent) + "%");
        
    }
    
    
    public static int calculateHandValue(ArrayList<Card> cards){
        int value = 0;
        boolean sawAnAce = false;
        for(Card card : cards) { // get value of hand
            value += calculateCardValue(card);
        }
        if(value > 21) { // assume aces are 1's
            value = 0;
            for(Card card : cards) {
            	if (card.getFace() == Card.Face.Ace && sawAnAce == false) {
            		value += calculateCardValue(card, true); 
            		sawAnAce = true;
            	} else if (card.getFace() == Card.Face.Ace && sawAnAce == true){
            		value += calculateCardValue(card, false);  
            	}
            }
        }
        return value;
    }
    
    public static int calculateCardValue(Card card){
        return calculateCardValue(card, false);
    }
    
    public static int calculateCardValue(Card card, boolean pickAltValue){
        switch(card.getFace()){
            case Two:
                return 2;
            case Three:
                return 3;
            case Four:
                return 4;
            case Five:
                return 5;
            case Six:
                return 6;
            case Seven:
                return 7;
            case Eight:
                return 8;
            case Nine:
                return 9;
            case Ten:
            case Jack:
            case Queen:
            case King:
                return 10;
            case Ace:
                if(pickAltValue){
                    return 1;
                }
                return 11;
            default:
                return 0;
        }
    }
}
