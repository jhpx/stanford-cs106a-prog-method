/* 
 * File: Fibonacci.java 
 * -------------------- 
 * This program lists the terms in the Fibonacci sequence up to 
 * a constant MAX_TERM_VALUE, which is the largest Fibonacci term 
 * the program will display. 
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {

    /* Defines the largest term to be displayed */
    private static final int MAX_TERM_VALUE = 10000;

    /** main method */
    public static void main(String[] args) {
        new Fibonacci().start(args);
    }

    public void run() {
        println("Ths program lists the Fibonacci sequence.");

        // Fib(0)=0 and Fib(1)=1
        int termCur = 1;
        int termPre = 0;

        // print the sequence step by step
        while (termCur < MAX_TERM_VALUE) {
            println(termCur);
            int termNext = termCur + termPre;
            termPre = termCur;
            termCur = termNext;
        }

    }

}