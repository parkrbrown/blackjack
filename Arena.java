/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 * This class is the driver class, where the main method is. It will ask the end user how many houses will be played and which kinds of users will be playing.
 * @author parkerbrown & Zach Bunyard
 */
public class Arena {

    private Scanner input = new Scanner(System.in);
    int numberOfPlayerWins;
    int numberOfHouseWins;
    int numberOfGames;

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) {//throws InterruptedException { // TODO: Program appears to work without this but you claim that we need it?

        Arena arena = new Arena();

        System.out.println("Welcome to Blackjack!");
        int numberOfGames = 100;//arena.getNumberOfGames(); // TODO: Comment back in after testing is complete
        // System.out.println("How many players will be playing against the house?"); // Stretch goal
        PlayerType playerType = PlayerType.USER;//arena.getPlayerType("What kind of player will be playing against the house?"); // TODO: Comment back in after testing is complete

        Player player = new Player("Player", playerType, arena.input);

        House house = new House(player, numberOfGames);
        
        //house.play();
        for (int i = 0; i < numberOfGames; i++) {
        	house.play();
        }

        house.showResults();
    }

    /**
     *
     * @return
     */
    public int getNumberOfGames() {
        while (true) {
            try {
                System.out.println("How many games would you like to play?");
                String value = input.nextLine();

                numberOfGames = Integer.parseInt(value);
                if (numberOfGames > 0) {
                    return numberOfGames;
                }
            } catch (NumberFormatException e) {
                System.out.print("That is not a valid number. Pleaes try again. ");
            }
        }
    }

    /**
     *
     * @param message
     * @return
     */
    public PlayerType getPlayerType(String message) {
        while (true) {
            try {
                System.out.println(message);
                return PlayerType.valueOf((input.next()).toUpperCase());
            } catch (Exception e) {
                System.out.print("That is not a valid player type. Pleaes choose user, random, or smart. ");
            }
        }
    }
}
