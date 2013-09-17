/* 
 * File: CheckWordLadder.java 
 * -------------------------- 
 * Solution for checking a word ladder from the practice final exam. 
 */

import acm.program.*;
import acm.util.RandomGenerator;

/** Checks to see whether a word ladder is legal */
public class CheckWordLadder extends ConsoleProgram {
    /** main method */
    public static void main(String[] args) {
        new CheckWordLadder().start(args);
    }

    public void run() {
        println("Program to check a word ladder.");
        println("Enter a sequence of words ending with a blank line.");
        String previous = null;
        String current = null;
        while (true) {
            while (true) {
                current = readLine();
                if (current.equals(""))
                    break;
                if (isLegalLadderPair(previous, current))
                    break;
                println("That word is not legal.  Try again.");
            }
            if (current.equals(""))
                break;
            previous = current;
        }
    }

    /** Method: isLegalLadderPair(previous, current) 
     * Checks to see if it is legal to link the two words in a 
     * word ladder. 
     */
    private boolean isLegalLadderPair(String previous, String current) {
        if (!lexicon.isEnglishWord(current))
            return false;
        if (previous == null)
            return true;
        if (previous.length() != current.length())
            return false;
        return countCharacterDifferences(previous, current) == 1;
    }

    /** Method: CountCharacterDifferences(s1, s2) 
     * Counts the number of character positions in s1 and s2 that contain 
     * different characters. 
     */
    private int countCharacterDifferences(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /* Private instance variables */
    private Lexicon lexicon = new Lexicon("english.dat");
    
    
    class Lexicon{
        
        public Lexicon(String s){
            
        }
        
        public boolean isEnglishWord(String s){
            RandomGenerator rgen = new RandomGenerator();
            return rgen.nextBoolean();
        }
    }
}