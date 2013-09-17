/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
    /* main method */
    public static void main(String[] args) {
        new CollectNewspaperKarel().start(args);
    }
	/*
	 * Pre-condition: A beeper outside the home Post-condition: No beepers
	 * outside the home
	 */
	public void run() {
		moveToNewspaper();
		pickUpNewspaper();
		returnToInitialPosition();
	}

	/*
	 * Pre-condition: Karel at home Post-condition: Karel at position of the
	 * outside beeper
	 */
	private void moveToNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}

	/*
	 * Pre-condition: No less than one beeper at current position of Karel
	 * Post-condition: One beeper less at current position of Karel
	 */
	private void pickUpNewspaper() {
		pickBeeper();
	}

	/*
	 * Pre-condition: Karel at position of the outside beeper Post-condition:
	 * Karel at home
	 */
	private void returnToInitialPosition() {
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnAround();
	}
}
