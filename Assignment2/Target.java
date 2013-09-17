/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

    /** basic radius value in pixels */
    private static final int CIRCLE_OUTER_RADUIS = 72;

    /* main method */
    public static void main(String[] args) {
        new Target().start(args);
    }

    public void run() {

        // draw the outer circle
        int circleOuterDiameter = CIRCLE_OUTER_RADUIS * 2;
        int circleOuterX = (getWidth() - circleOuterDiameter) / 2;
        int circleOuterY = (getHeight() - circleOuterDiameter) / 2;

        GOval circleOuter =
                new GOval(circleOuterX, circleOuterY, circleOuterDiameter,
                        circleOuterDiameter);
        circleOuter.setFilled(true);
        circleOuter.setFillColor(Color.red);
        add(circleOuter);

        // draw the middle circle
        int circleMiddleDiameter = (int) (CIRCLE_OUTER_RADUIS * 2 * 0.65);
        int circleMiddleX = (getWidth() - circleMiddleDiameter) / 2;
        int circleMiddleY = (getHeight() - circleMiddleDiameter) / 2;

        GOval circleMiddle =
                new GOval(circleMiddleX, circleMiddleY, circleMiddleDiameter,
                        circleMiddleDiameter);
        circleMiddle.setFilled(true);
        circleMiddle.setFillColor(Color.white);
        add(circleMiddle);

        // draw the inner circle
        int circleInnerDiameter = (int) (CIRCLE_OUTER_RADUIS * 2 * 0.3);
        int circleInnerX = (getWidth() - circleInnerDiameter) / 2;
        int circleInnerY = (getHeight() - circleInnerDiameter) / 2;

        GOval circleInner =
                new GOval(circleInnerX, circleInnerY, circleInnerDiameter,
                        circleInnerDiameter);
        circleInner.setFilled(true);
        circleInner.setFillColor(Color.red);
        add(circleInner);
    }
}
