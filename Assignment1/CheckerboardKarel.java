/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new CheckerboardKarel().start(args);
    }
    
	/* 
	 * Pre-condition: World is balnk
	 * Post-condition: World is filled with fixed beeper partens
	 * and it looks like: 
	 * -------------				---
	 * | + + + + + |				|+|
	 * |+ + + + + +|				| |
	 * | + + + + + |				|+|				----------
	 * |+ + + + + +|		or		| |		or		|+ + + + |
	 * | + + + + + |				|+|				----------
	 * |+ + + + + +|				---
	 * -------------
	 */
	public void run() {
		createCheckLine();

		while (leftIsClear()) {
			changeCheckLineType();
			repositionForUpperRow();
			createCheckLine();

			if (rightIsClear()) {
				changeCheckLineType();
				repositionForUpperRow();
				createCheckLine();
			} else {
				turnAround();
			}
		}

		turnNorth();
	}

	/* Create a check line such as |+ + + ...| or | + + + ...| */
	private void createCheckLine() {
		if (notFacingSouth()) {
			putBeeper();
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				}
			}
		} else {
			turnToInside();
			while (frontIsClear()) {
				move();
				putBeeper();
				if (frontIsClear()) {
					move();
				}
			}
		}
	}

	/* Make Karel back to a wall */
	private void turnToInside() {
		//facing south
		if (facingSouth()) {
			if (leftIsClear()) {
				turnLeft();
			} else if (rightIsClear()) {
				turnRight();
			} else {
				turnAround();
			}
		}
		//facing east or west
		else {
			if (frontIsBlocked()) {
				turnAround();
			}
		}

	}

	/* Make Karel know how to draw next check line
	 * Pre-condition: Facing east or west after creating a CheckLine
	 * Post-condition: Facing south when there is a beeper at current 
	 * position or facing original direction otherwise
	 */
	private void changeCheckLineType() {
		if (beepersPresent()) {
			turnSouth();
		}
	}

	/* Make Karel on the north position of its previous position */
	private void repositionForUpperRow() {
		if (facingEast()) {
			turnLeft();
			move();
			turnLeft();
		} else if (facingWest()) {
			turnRight();
			move();
			turnRight();
		} else if (facingSouth()) {
			turnAround();
			move();
			turnAround();
		}
	}

	/* Make Karel facing north */
	private void turnNorth() {
		if (facingEast()) {
			turnLeft();
		} else if (facingWest()) {
			turnRight();
		} else if (facingSouth()) {
			turnAround();
		}
	}

	/* Make Karel facing south */
	private void turnSouth() {
		if (facingEast()) {
			turnRight();
		} else if (facingWest()) {
			turnLeft();
		} else if (facingNorth()) {
			turnAround();
		}
	}
}
