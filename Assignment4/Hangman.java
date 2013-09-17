/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {
    /** main method */
    public static void main(String[] args) {
        new Hangman().start(args);
    }

    /** prepare graphic canvas for the game */
    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }

    /** begin the game */
    public void run() {
        hl = new HangmanLexicon();
        rgen = new RandomGenerator();

        println("Welcome to Hangman.");

        String str = wordPrepare();
        canvas.reset();
        canvas.displayWord(wordGuess);

        while (true) {
            println("The word now looks like this:" + wordGuess);
            println("You have " + numGuessWrong + " guesses left.");

            char chInput = readUpperCaseChar();
            int index = str.indexOf(chInput);

            if (index == -1) {
                numGuessWrong--;
                println("There are no " + chInput + "'s in the word.");
                canvas.noteIncorrectGuess(chInput);

                if (numGuessWrong <= 0) {
                    println("You're completely hung.");
                    println("The word was: " + str);
                    println("You Lose.");
                    break;
                }
            } else {
                println("That guess is correct.");

                while (index != -1) {
                    wordGuess =
                            wordGuess.substring(0, index) + chInput
                                    + wordGuess.substring(index + 1);
                    index = str.indexOf(chInput, index + 1);

                }

                canvas.displayWord(wordGuess);

                if (wordGuess.equals(str)) {
                    println("You guessed the word " + wordGuess);
                    println("You win.");
                    break;
                }
            }

        }

    }

    /** prepare a word to guess */
    private String wordPrepare() {
        /* generate a word */
        String str = new String(hl.getWord(rgen.nextInt(hl.getWordCount())));
        wordGuess = "";
        numGuessWrong = 8;
        for (int j = 0; j < str.length(); j++) {
            wordGuess += "-";
        }
        return str;
    }

    /** accept a word and change it into upper case */
    private char readUpperCaseChar() {
        while (true) {
            String input = readLine("You guess:");
            char chInput = input.charAt(0);
            if (Character.isUpperCase(chInput)) {
                return chInput;
            } else if (Character.isLowerCase(chInput)) {
                return Character.toUpperCase(chInput);
            } else {
                println("Illegal Chracter! Please try another one.");
            }
        }
    }

    private HangmanLexicon hl;
    private RandomGenerator rgen;
    private int numGuessWrong;

    private String wordGuess;
    private HangmanCanvas canvas;
}
