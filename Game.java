/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 * This class is for coordinating with the players (e.g., finding out what move the current player would like to make), declaring the winner, etc.
 * @author parkerbrown
 */
public class Game {

    private final Player PLAYER1;
    private final Player PLAYER2;
    private int sticksLeft;
    private final int NUMBER_OF_GAMES;
    private final int NUMBER_OF_STICKS;
    //private final int MAX_STICKS_THAT_CAN_BE_TAKEN;

    private Player currentTurn;

    private boolean shouldShowGameplay() {
        return PLAYER1.getPlayerType() == PlayerType.USER || PLAYER2.getPlayerType() == PlayerType.USER;
    }

    /**
     *
     * @param player1
     * @param player2
     * @param numberOfGames
     */
    public Game(Player player1, Player player2, int numberOfGames) {
        this.PLAYER1 = player1;
        this.PLAYER2 = player2;
        this.PLAYER1.setShouldShowGameplay(shouldShowGameplay());
        this.PLAYER2.setShouldShowGameplay(shouldShowGameplay());
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
        sticksLeft = NUMBER_OF_STICKS;
        if (shouldShowGameplay()) {
            System.out.println("Okay, " + PLAYER1.getPlayerTypeName() + " vs " + PLAYER2.getPlayerTypeName() + ".");
        }
        currentTurn = PLAYER1;

        while (sticksLeft > 0) {
            if (shouldShowGameplay()) {
                printSticks();
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
            System.out.println("Okay, Player 1 (" + PLAYER1.getPlayerTypeName() + ") and Player 2 (" + PLAYER2.getPlayerTypeName() + ") just finished playing " + NUMBER_OF_GAMES + " games.  Here is the outcome:");
        }
        System.out.println(PLAYER1.getPlayerTypeName() + " wins: " + PLAYER1.getWins());
        System.out.println(PLAYER2.getPlayerTypeName() + " wins: " + PLAYER2.getWins());
    }

    private void switchTurns() {
        if (currentTurn == PLAYER1) {
            currentTurn = PLAYER2;
        } else {
            currentTurn = PLAYER1;
        }
    }
}
