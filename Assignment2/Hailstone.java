/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
    /* main method */
    public static void main(String[] args) {
        new Hailstone().start(args);
    }

    public void run() {
        // Read number from user
        int a = readInt("Enter a number:");

        // count steps
        int count = 0;

        // print the sequence step by step
        while (true) {
            if (a == 1)
                break;
            print(a);
            if (a % 2 != 0) {
                a = 3 * a + 1;
                println(" is odd, so I make 3n + 1: " + a);
            } else {
                a = a / 2;
                println(" is even, so I take half: " + a);
            }
            count++;
        }

        // print the OK information
        println("The process took " + count + " to reach 1");
    }
}
