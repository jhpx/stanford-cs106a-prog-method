/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
    /* main method */
    public static void main(String[] args) {
        new PythagoreanTheorem().start(args);
    }

    public void run() {
        println("Enter values to compute Pythagorean theorem.");
        int a = readInt("a:");
        int b = readInt("b:");
        // calculate c=sqrt(a^2+b^2)
        double c = Math.sqrt(a * a + b * b);
        println("c = " + c);
    }
}
