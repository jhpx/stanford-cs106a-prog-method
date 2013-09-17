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
/*
 * put one beeper at the middle point of the 1st street.
 */
import stanford.karel.*;

public class MidpointFindingKarel2 extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new MidpointFindingKarel2().start(args);
    }

    public void run() {
        putOneLine(); // fill the 1st street with beepers except 1st and the
                      // last avenues.
        turnBack();
        // pick up beside beepers
        while (beepersPresent()) {
            pickBesideBeeper();
            move();
        }
        // put one beeper at the middle point
        turnBack();
        putBeeper();
    }

    private void putOneLine() {
        move();
        while (frontIsClear()) {
            putBeeper();
            move();
        }
    }

    private void pickBesideBeeper() {
        while (beepersPresent()) {
            move();
        }
        turnBack();
        pickBeeper();
    }

    private void turnBack() {
        turnAround();
        move();
    }
}