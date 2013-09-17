/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

    private static final int SENTINEL = 0;

    /* main method */
    public static void main(String[] args) {
        new FindRange().start(args);
    }

    public void run() {
        int value, max, min;
        println("This program finds the largest and smallest numbers.");
        value = readInt("? ");
        if (value == SENTINEL) {
            println("smallest: None");
            println("largest: None");
            return;
        } else {
            max = value;
            min = value;
        }
        while (true) {
            value = readInt("? ");
            if (value == SENTINEL)
                break;
            if (value > max)
                max = value;
            if (value < min)
                min = value;
        }
        println("smallest:" + max);
        println("largest:" + min);
    }

}
