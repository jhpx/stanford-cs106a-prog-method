/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new MidpointFindingKarel().start(args);
    }

    /*
     * Pre-condition: Karel at 1st Avenue and 1st Street, facing east
     * Post-condition: Karel at the middle point of 1st street with a beeper
     * there
     */
    public void run() {
        Beeperinit();
        while (notFacingNorth()) {
            findFirstBeeper();
            MakeBeeperCloser();
        }
        turnRight();
    }

    /*
     * Pre-condition: Facing east at 1st Avenue and 1st Street Post-condition:
     * Facing west and there is one beeper at both the start point and the end
     * point of 1st Street
     */
    private void Beeperinit() {
        putBeeper();
        moveToWall();
        putBeeper();
        turnAround();
    }

    /*
     * Pre-condition: None Post-condition: Facing first beeper in whichever
     * direction Karel was facing previously
     */
    private void findFirstBeeper() {
        if (frontIsClear()) {
            move();
        }
        while (noBeepersPresent()) {
            move();
        }
    }

    /*
     * Pre-condition: None Post-condition: Facing first wall in whichever
     * direction Karel was facing previously
     */
    private void moveToWall() {
        while (frontIsClear()) {
            move();
        }
    }

    /*
     * Pre-condition: Facing east or west at a position with a beeper
     * Post-condition: Facing the oppsite direction at its backward position
     * with that beeper, or facing north when there is already a beeper in
     * Karel's backward position
     */
    private void MakeBeeperCloser() {
        turnAround();
        if (frontIsClear()) {
            pickBeeper();
            move();
            if (noBeepersPresent()) {
                putBeeper();
            } else {
                if (facingEast()) {
                    turnLeft();
                } else {
                    turnRight();
                }
            }
        }

    }
}
