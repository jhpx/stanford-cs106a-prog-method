/* File: InnerBorderKarel.java */
import stanford.karel.*;

public class InnerBorderKarel extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new InnerBorderKarel().start(args);
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            moveInnerWithSameDir();
            moveToWallWithBeeper();
        }
    }

    private void moveInnerWithSameDir() {

        if (leftIsClear()) {
            turnLeft();
            move();
            turnRight();
        }
    }

    private void moveToWallWithBeeper() {
        if (frontIsClear()) {
            move();
        }

        while (frontIsClear()) {
            if (noBeepersPresent()) {
                putBeeper();
            }
            move();
        }
        turnLeft();

    }

}