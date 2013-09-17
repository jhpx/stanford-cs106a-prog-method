/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
    /** main method */
    public static void main(String[] args) {
        new Yahtzee().start(args);
    }

    /** initialize the game setup */
    public void init() {
        IODialog dialog = getDialog();
        nPlayers = dialog.readInt("Enter number of players");
        playerNames = new String[nPlayers];
        for (int i = 1; i <= nPlayers; i++) {
            playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
        }
        display = new YahtzeeDisplay(getGCanvas(), playerNames);
        diceArray = new int[N_DICE];
        scoreArray = new int[nPlayers][N_CATEGORIES];
    }

    /** begin the game */
    public void run() {
        for (int cat = 0; cat < N_SCORING_CATEGORIES; cat++)
            for (int player = 0; player < nPlayers; player++) {
                playTurn(player);
            }
        for (int player = 0; player < nPlayers; player++) {
            statisticsScore(player);
        }
        judgeWinner();
    }

    /**
     * run a turn for a single player
     * 
     * @param player
     * */
    private void playTurn(int player) {
        determineDice(player);
        int category = determineCategory();
        determineScore(category, player);

    }

    /**
     * Determine the dice and display it
     * 
     * @param player
     * */
    private void determineDice(int player) {
        display.printMessage(playerNames[player]
                + "'s turn.  Click \"Roll Dice\" button to roll the dice.");
        display.waitForPlayerToClickRoll(player + 1);

        initDice();
        rollDice();
        display.displayDice(diceArray);

        display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
        display.waitForPlayerToSelectDice();
        if (selectDice() == 0)
            return;
        rollDice();
        display.displayDice(diceArray);

        display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
        display.waitForPlayerToSelectDice();
        if (selectDice() == 0)
            return;
        rollDice();
        display.displayDice(diceArray);

    }

    /** Initialize the Dice Array */
    private void initDice() {
        selectedDiceArray = new boolean[N_DICE];
        for (int i = 0; i < N_DICE; i++) {
            selectedDiceArray[i] = true;
        }
    }

    /** Get value for the Dice Array */
    private void rollDice() {
        for (int i = 0; i < N_DICE; i++) {
            if (selectedDiceArray[i]) {
                diceArray[i] = rgen.nextInt(1, 6);
                // diceArray[i] = (getDialog().readInt("Enter number:")) % 6;
            }
        }
    }

    /** ask the player select some dice */
    private int selectDice() {
        int countSelected = 0;
        if (selectedDiceArray == null) {
            initDice();
        }
        for (int i = 0; i < N_DICE; i++) {
            if (display.isDieSelected(i)) {
                selectedDiceArray[i] = true;
                countSelected++;
            } else {
                selectedDiceArray[i] = false;
            }
        }
        return countSelected;
    }

    private int determineCategory() {
        display.printMessage("Select a category for this roll.");
        return display.waitForPlayerToSelectCategory();
    }

    /** Determine the score and display it */
    private void determineScore(int category, int player) {
        int score = calculateScore(category, player);
        scoreArray[player][category - 1] = score;
        display.updateScorecard(category, player + 1,
                scoreArray[player][category - 1]);

        scoreArray[player][TOTAL - 1] += score;
        display.updateScorecard(TOTAL, player + 1,
                scoreArray[player][TOTAL - 1]);

    }

    /** Calculate a score for given player and selected category */
    private int calculateScore(int category, int player) {
        if (checkCategory(diceArray, category)) {
            int score = 0;

            switch (category) {
            case ONES:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 1) {
                        score += diceArray[i];
                    }
                }
                break;
            case TWOS:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 2) {
                        score += diceArray[i];
                    }
                }
                break;
            case THREES:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 3) {
                        score += diceArray[i];
                    }
                }
                break;
            case FOURS:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 4) {
                        score += diceArray[i];
                    }
                }
                break;
            case FIVES:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 5) {
                        score += diceArray[i];
                    }
                }
                break;
            case SIXES:
                for (int i = 0; i < diceArray.length; i++) {
                    if (diceArray[i] == 6) {
                        score += diceArray[i];
                    }
                }
                break;
            case THREE_OF_A_KIND:
                for (int i = 0; i < diceArray.length; i++) {
                    score += diceArray[i];
                }
                break;
            case FOUR_OF_A_KIND:
                for (int i = 0; i < diceArray.length; i++) {
                    score += diceArray[i];
                }
                break;
            case FULL_HOUSE:
                score = 25;
                break;
            case SMALL_STRAIGHT:
                score = 30;
                break;
            case LARGE_STRAIGHT:
                score = 40;
                break;
            case YAHTZEE:
                score = 50;
                break;
            case CHANCE:
                for (int i = 0; i < diceArray.length; i++) {
                    score += diceArray[i];
                }
                break;
            default:
                break;
            }
            return score;
        } else {
            return 0;
        }
    }

    private void statisticsScore(int player) {
        for (int i = ONES; i <= SIXES; i++) {
            scoreArray[player][UPPER_SCORE - 1] += scoreArray[player][i - 1];
            display.updateScorecard(UPPER_SCORE, player + 1,
                    scoreArray[player][UPPER_SCORE - 1]);
        }

        if (scoreArray[player][UPPER_SCORE - 1] >= 63) {
            scoreArray[player][UPPER_BONUS - 1] = 35;
            scoreArray[player][TOTAL - 1] += 35;
            display.updateScorecard(TOTAL, player + 1,
                    scoreArray[player][TOTAL - 1]);
        }
        display.updateScorecard(UPPER_BONUS, player + 1,
                scoreArray[player][UPPER_BONUS - 1]);

        for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
            scoreArray[player][LOWER_SCORE - 1] += scoreArray[player][i - 1];
            display.updateScorecard(LOWER_SCORE, player + 1,
                    scoreArray[player][LOWER_SCORE - 1]);
        }
    }

    private void judgeWinner() {
        ArrayList<Integer> list = findMaxScoreIndex();
        StringBuffer str = new StringBuffer();
        str.append("Congratulations, ");
        for (int player : list) {
            str.append(playerNames[player] + ", ");
        }
        str.append("you are the winner with a total score of "
                + scoreArray[list.get(0)][TOTAL - 1] + "!");
        display.printMessage(str.toString());
    }

    private ArrayList<Integer> findMaxScoreIndex() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int max = 0;
        for (int i = 0; i < scoreArray.length; i++) {
            max = Math.max(scoreArray[i][TOTAL - 1], max);
        }
        for (int i = 0; i < scoreArray.length; i++) {
            if (max == scoreArray[i][TOTAL - 1]) {
                result.add(i);
            }
        }
        return result;

    }

    /**Checks to see whether the dice array fits the category.*/
    private static boolean checkCategory(int[] diceArray, int category) {
        boolean returnFlag = false;

        switch (category) {
        case ONES:
        case TWOS:
        case THREES:
        case FOURS:
        case FIVES:
        case SIXES:
            returnFlag = true;
            break;
        case THREE_OF_A_KIND: {
            int[] tmpCNT = new int[6];
            for (int i = 0; i < diceArray.length; i++) {
                tmpCNT[diceArray[i] - 1]++;
            }
            for (int i = 0; i < tmpCNT.length; i++) {
                if (tmpCNT[i] >= 3) {
                    returnFlag = true;
                    break;
                }
            }
            break;
        }
        case FOUR_OF_A_KIND: {
            int[] tmpCNT = new int[6];
            for (int i = 0; i < diceArray.length; i++) {
                tmpCNT[diceArray[i] - 1]++;
            }
            for (int i = 0; i < tmpCNT.length; i++) {
                if (tmpCNT[i] >= 4) {
                    returnFlag = true;
                    break;
                }
            }
            break;
        }
        case FULL_HOUSE: {
            int[] tmpCNT = new int[6];
            boolean returnFlagPart1 = false;
            boolean returnFlagPart2 = false;
            for (int i = 0; i < diceArray.length; i++) {
                tmpCNT[diceArray[i] - 1]++;
            }
            for (int i = 0; i < tmpCNT.length; i++) {
                if (tmpCNT[i] >= 3) {
                    if (returnFlagPart1 == false)
                        returnFlagPart1 = true;
                    else
                        returnFlagPart2 = true;
                } else if (tmpCNT[i] == 2) {
                    returnFlagPart2 = true;
                }
            }
            returnFlag = returnFlagPart1 && returnFlagPart2;
            break;
        }
        case SMALL_STRAIGHT: {
            boolean[] tmp = new boolean[6];
            int totalTrue = 0;
            for (int i = 0; i < diceArray.length; i++) {
                if (tmp[diceArray[i] - 1] == false)
                    totalTrue++;
                tmp[diceArray[i] - 1] = true;
            }
            if (totalTrue < 4) {
                returnFlag = false;
            } else {
                int numTrue = 0;
                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i] == false) {
                        numTrue = 0;
                    } else {
                        numTrue++;
                    }
                    if (numTrue >= 4) {
                        returnFlag = true;
                        break;
                    }
                }
            }
            break;
        }
        case LARGE_STRAIGHT: {
            boolean[] tmp = new boolean[6];
            int totalTrue = 0;
            for (int i = 0; i < diceArray.length; i++) {
                if (tmp[diceArray[i] - 1] == false)
                    totalTrue++;
                tmp[diceArray[i] - 1] = true;
            }
            if (totalTrue < 5) {
                returnFlag = false;
            } else {
                int numTrue = 0;
                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i] == false) {
                        numTrue = 0;
                    } else {
                        numTrue++;
                    }
                    if (numTrue >= 5) {
                        returnFlag = true;
                        break;
                    }
                }
            }
            break;
        }
        case YAHTZEE:
            for (int i = 0; i < diceArray.length - 1; i++) {
                if (diceArray[i] != diceArray[i + 1]) {
                    returnFlag = false;
                    break;
                }
            }
            break;
        case CHANCE:
            return true;
        default:
            break;
        }
        return returnFlag;
    }

    /* Private instance variables */
    private int nPlayers;
    private String[] playerNames;
    private YahtzeeDisplay display;
    private RandomGenerator rgen = new RandomGenerator();

    private int[] diceArray;
    private boolean[] selectedDiceArray;
    private int[][] scoreArray;

}
