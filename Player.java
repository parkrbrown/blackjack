/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a player.  Depending on the type of Player this class is constructed with, it should behave accordingly.
 * @author parkerbrown
 */
public class Player {

    private Scanner scanner;
    private PlayerType playerType;
    private String name;
    private Random random;
    private int wins;
    private int loses;
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

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the player type
     * @return
     */
    public String getPlayerTypeName() {
        switch (playerType) {
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
    }

    /**
     * This method indicates how many sticks the player would like to take
     *
     * @param sticksLeft
     * @return
     */
    public int takeSticks(int sticksLeft) {
        switch (playerType) {
            case USER:
                return userPlayer();
            case RANDOM:
                return randomPlayer();
            case SMART:
                return smartPlayer(sticksLeft);
            default:
                return userPlayer();
        }
    }

    private int smartPlayer(int sticksLeft) {
        int sticks = 0;
        // TARGET NUMBERS:  13, 9, 5, 1
        switch (sticksLeft) {
            case 15:
                sticks = 2; // to get to target number 13
                break;
            case 14:
                sticks = 1; // to get to target number 13
                break;
            case 13:
                sticks = 2; // shot in the dark
                break;
            case 12:
                sticks = 3; // to get to target number 9
                break;
            case 11:
                sticks = 2; // to get to target number 9
                break;
            case 10:
                sticks = 1; // to get to target number 9
                break;
            case 9:
                sticks = 2; // shot in the dark
                break;
            case 8:
                sticks = 3; // to get the target number 5
                break;
            case 7:
                sticks = 2; // to get to target number 5
                break;
            case 6:
                sticks = 1; // to get to target number 5
                break;
            case 5:
                sticks = 2; // shot in the dark
                break;
            case 4:
                sticks = 3; // to get to target number 1
                break;
            case 3:
                sticks = 2; // to get to target number 1
                break;
            case 2:
                sticks = 1; // to get to target number 1
                break;
            case 1:
                sticks = 1; // defeat
                break;
            default:
                break; //
        }
        if (shouldShowGameplay) {
            System.out.println(name + " took " + convertIntToStringName(sticks) + (sticks == 1 ? " matchstick" : " matchsticks") + ".");
        }
        return sticks;
    }

    private int randomPlayer() {
        int sticks = random.nextInt(3) + 1;
        if (shouldShowGameplay) {
            System.out.println(name + " took " + convertIntToStringName(sticks) + (sticks == 1 ? " matchstick" : " matchsticks") + ".");
        }
        return sticks;
    }

    private int userPlayer() {
        int sticks = 0;
        while (true) {
            try {
                System.out.println(name + " how many sticks would you like to take?:");
                sticks = scanner.nextInt();
                if (sticks <= 3 && sticks >= 1) {
                    break;
                } else {
                    System.out.print("You can't take that many sticks. ");
                }
            } catch (Exception e) {
                
            }
        }
        System.out.println("Okay, " + convertIntToStringName(sticks) + ".");
        return sticks;
    }

    private String convertIntToStringName(int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return number + "";
        }
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
            System.out.println(name + " took the last matchstick!");
        }
    }

}