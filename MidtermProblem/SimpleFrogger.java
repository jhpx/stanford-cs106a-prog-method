/* 
 * File: SimpleFrogger.java 
 * ------------------------ 
 * This program solves the Frogger problem from the practice midterm. 
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

/* 
 * This program gets a frog to jump one square in the closest 
 * direction to a mouse click. 
 */
public class SimpleFrogger extends GraphicsProgram {

	public static final int SQSIZE = 75;
	public static final int NCOLS = 7;
	public static final int NROWS = 3;

	public static final int APPLICATION_WIDTH = SQSIZE * NCOLS;
	public static final int APPLICATION_HEIGHT = SQSIZE * NROWS + 15;

    /* main method */
    public static void main(String[] args) {
        new SimpleFrogger().start(args);
    }
    
	public void run() {

		frogInit();
		addMouseListeners();

	}

	private void frogInit() {
		frog = new GImage("frog.gif");
		frogX = SQSIZE * (NCOLS / 2 + 0.5);
		frogY = SQSIZE * (NROWS - 0.5);
		frog.setLocation(frogX - frog.getWidth() / 2, frogY - frog.getHeight()
				/ 2);
		add(frog);
	}

	public void mouseClicked(MouseEvent e) {

		double dx = e.getX() - frogX;
		double dy = e.getY() - frogY;

		if (Math.abs(dx) > Math.abs(dy)) {
			frogJump(Math.signum(dx) * SQSIZE, 0);

		} else {
			frogJump(0, Math.signum(dy) * SQSIZE);

		}
	}

	private void frogJump(double px, double py) {
		if (insideFroggerWorld(frogX + px, frogY + py)) {
			frog.move(px, py);
			frogX += px;
			frogY += py;
		}
	}

	private boolean insideFroggerWorld(double x, double y) {
		return (x >= 0 && x <= NCOLS * SQSIZE && y >= 0 && y <= NROWS * SQSIZE);
	}

	private GImage frog;
	private double frogX;
	private double frogY;
}