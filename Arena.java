/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 * This class is the driver class, where the main method is. It will ask the end user how many games will be played and which kinds of users will be playing.
 * @author parkerbrown
 */
public class Arena {

    private Scanner input = new Scanner(System.in);
    int numberOfPlayerOneWins;
    int numberOfPlayerTwoWins;
    int numberOfGames;

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Arena arena = new Arena();

        System.out.println("Welcome to Blackjack!");
        int numberOfGames = arena.getNumberOfGames();
        PlayerType player1Type = arena.getPlayerType("What kind of player will Player 1 be?");
        PlayerType player2Type = arena.getPlayerType("What kind of player will Player 2 be?");

        Player player1 = new Player("Player One", player1Type, arena.input);
        Player player2 = new Player("Player Two", player2Type, arena.input);

        Game game = new Game(player1, player2, numberOfGames);

        for (int i = 0; i < numberOfGames; i++) {
            game.play();
        }

        game.showResults();

        //Thread.sleep(2000);  // This short pause happens by nature when running a large number of games. 
        //arena.printResults();
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
