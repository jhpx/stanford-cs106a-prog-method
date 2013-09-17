/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new StoneMasonKarel().start(args);
    }

    /*
     * Pre-condition: Facing east at bottom of a column with missing beepers
     * Post-condition: Facing first wall and making all passed columns filled
     * with full beepers
     */
    public void run() {
        while (facingEast()) {
            putBeeperColumn();
            goToNextColumn();
        }
        turnLeft();
    }

    /*
     * Pre-condition: Facing east at bottom of a column with full beepers
     * Post-condition: Facing east at bottom of next column to be filled with
     * beepers or facing south in front of a wall
     */
    private void goToNextColumn() {
        for (int i = 0; i < 4; i++) {
            if (frontIsClear()) {
                move();
            } else {
                if (facingEast()) {
                    turnRight();
                }
            }
        }
    }

    /*
     * Pre-condition: none Post-condition: Facing first wall in whichever
     * direction Karel was facing previously
     */
    private void moveToWall() {
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Pre-condition: Facing east at bottom of a column with missing beepers
     * Post-condition: Facing east at bottom of the same column with full
     * beepers
     */
    private void putBeeperColumn() {
        turnLeft();
        moveToWall();
        turnAround();
        if (noBeepersPresent()) {
            putBeeper();
        }
        while (frontIsClear()) {
            move();
            if (noBeepersPresent()) {
                putBeeper();
            }
        }
        turnLeft();
    }
}
