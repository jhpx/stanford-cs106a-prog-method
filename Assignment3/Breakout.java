/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

    /* ================== private final constant ================== */

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 7;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 7;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1)
            * BRICK_SEP)
            / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

    /** Animation delay or pause time between ball moves */
    private static final int DELAY = 30;

    /* ======================== public methods ======================== */
    /** main method */
    public static void main(String[] args) {
        new Breakout().start(args);
    }

    /** Runs the Breakout program. */
    public void run() {
        /* You fill this in, along with any subsidiary methods */
        setup();
        play();
    }

    /** Sets up everything in the game */
    public void setup() {
        setupBricks();
        setupPaddle();
        setupBall();
        setupPrompt();
        setupScore();
        addMouseListeners();
    }

    /** Makes everything work that means one can play this game */
    public void play() {
        for (int i = 0; i < NTURNS; i++) {
            startGame();

            while (ball.getY() < HEIGHT) {
                ball.move(vx, vy);
                checkForCollision();
                pause(DELAY);
                if (numBricks <= 0) {
                    break;
                }
            }
        }
        if (numBricks == 0) {
            endGame("Congratulations! You win!");
        } else {
            endGame("Game Over");
        }
    }

    /** Sets up the brick rows on the top */
    private void setupBricks() {

        numBricks = 0;

        for (int i = 0; i < NBRICK_ROWS; i++) {

            // draw a row of bricks
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {

                int brickX = (BRICK_WIDTH + BRICK_SEP) * j;
                int brickY = BRICK_Y_OFFSET + BRICK_HEIGHT * i;
                GRect brick =
                        new GRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);

                // change the color of the bricks which remain constant for two
                // rows and run in the following rainbow-like sequence: RED,
                // ORANGE, YELLOW, GREEN, CYAN.
                switch (i) {
                case 0:
                case 1:
                    brick.setFillColor(Color.RED);
                    break;
                case 2:
                case 3:
                    brick.setFillColor(Color.ORANGE);
                    break;
                case 4:
                case 5:
                    brick.setFillColor(Color.YELLOW);
                    break;
                case 6:
                case 7:
                    brick.setFillColor(Color.GREEN);
                    break;
                case 8:
                case 9:
                    brick.setFillColor(Color.CYAN);
                    break;
                }
                brick.setColor(Color.WHITE);
                brick.setFilled(true);

                add(brick);
                numBricks++;
            }
        }
    }

    /** Sets up the paddle on the bottom */
    private void setupPaddle() {
        int paddleX = (WIDTH - PADDLE_WIDTH) / 2;
        int paddleY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);

        add(paddle);
    }

    /** Sets up the ball in the middle */
    private void setupBall() {
        int ballX = WIDTH / 2 - BALL_RADIUS;
        int ballY = HEIGHT / 2 - BALL_RADIUS;
        ball = new GOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);

        ball.setFilled(true);
        ball.setFillColor(Color.BLACK);
        ball.setVisible(false);
        add(ball);
    }

    /** Sets up the prompt information */
    private void setupPrompt() {
        prompt = new GLabel("Click to Start");
        prompt.setFont(new Font("Times New Roman", Font.BOLD, 16));
        double promptX = (WIDTH - prompt.getWidth()) / 2;
        double promptY = (HEIGHT - prompt.getAscent()) / 2;
        prompt.setLocation(promptX, promptY);

        prompt.setVisible(false);
        add(prompt);
    }

    /** Sets up the score information */
    private void setupScore() {
        scoreInfo = new GLabel("Score:" + score);
        scoreInfo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        double scoreX = 10;
        double scoreY = 10;
        scoreInfo.setLocation(scoreX, scoreY);

        scoreInfo.setVisible(true);
        add(scoreInfo);
    }

    /** start the game */
    private void startGame() {

        prompt.setVisible(true);

        waitForClick();

        score = 0;
        updateScoreInfo();

        prompt.setVisible(false);

        ball.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
        ball.setVisible(true);

        paddle.setLocation((WIDTH - PADDLE_WIDTH) / 2, HEIGHT - PADDLE_Y_OFFSET
                - PADDLE_HEIGHT);

        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        vy = 3.0;
    }

    /** end the game */
    private void endGame(String str) {
        prompt.setLabel(str);
        double promptX = (WIDTH - prompt.getWidth()) / 2;
        double promptY = (HEIGHT - prompt.getAscent()) / 2;
        prompt.setLocation(promptX, promptY);
        prompt.setVisible(true);
        paddle.setVisible(false);
        ball.setVisible(false);
    }

    /**
     * Determine if collision with something,including edge of the game zone,
     * the paddle and the bricks, update velocities and location as appropriate.
     */
    private void checkForCollision() {
        checkEdge();
        checkObject();
    }

    /**
     * Determine if collision with floor or wall, update velocities and location
     * as appropriate.
     */
    private void checkEdge() {

        // determine if ball has dropped above the top
        if (ball.getY() < 0) {
            // change ball's Y velocity to now bounce downwards
            vy = -vy;
            // assume bounce will move ball an amount below the
            // top equal to the amount it would have risen
            // above the top.
            ball.move(0, -ball.getY());
        }

        // determine if ball has dropped east to the east wall
        if (ball.getX() > WIDTH - BALL_RADIUS * 2) {
            // change ball's X velocity to now bounce west
            vx = -vx;
            // assume bounce will move ball an amount west to the
            // east wall equal to the amount it would have moved
            // east to the east wall.
            ball.move(0, (WIDTH - BALL_RADIUS * 2) - ball.getX());
        }

        // determine if ball has dropped west to the west wall
        else if (ball.getX() < 0) {
            // change ball's X velocity to now bounce east
            vx = -vx;
            // assume bounce will move ball an amount east to the
            // west wall equal to the amount it would have moved
            // west to the west wall.
            ball.move(0, -ball.getX());
        }
    }

    /**
     * Determine if collision with paddle or bricks, update velocities and
     * location as appropriate.
     */
    private void checkObject() {
        GObject collider = getCollidingObject();
        if (collider == null) {
            return;
        } else if (collider.getClass().getName() == "acm.graphics.GRect") {
            bounceClip.play();
            if (collider == paddle) {
                vy = vy < 0 ? vy : -vy;
                // assume bounce will move ball an amount above the
                // paddle equal to the amount it would have dropped
                // below the paddle.
                double diff = ball.getY() - (paddle.getY() - BALL_RADIUS * 2);
                ball.move(0, -diff);
            } else {
                vy = -vy;
                remove(collider);
                numBricks--;
                score += 100;
                updateScoreInfo();
            }
        }
    }

    /** @return the object involved in the collision, if any, and null otherwise. */
    private GObject getCollidingObject() {

        // check 4 corners of the ball to judge whether there exists an object
        // to get. this work is down as following sequence: lefttop, leftbottom,
        // righttop, rightbottom
        for (int i = 0; i < 4; i++) {
            double ballX = (i & 0x0010) * BALL_RADIUS * 2 + ball.getX();
            double ballY = (i & 0x0001) * BALL_RADIUS * 2 + ball.getY();
            GObject obj = getElementAt(ballX, ballY);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    /** Called on mouse press to select the paddle */
    public void mousePressed(MouseEvent e) {
        mouseLastX = e.getX();
    }

    /** Called on mouse drag to move the paddle */
    public void mouseDragged(MouseEvent e) {
        paddle.move(e.getX() - mouseLastX, 0);
        mouseLastX = e.getX();
    }

    /** Update the score information */
    private void updateScoreInfo() {
        scoreInfo.setLabel("Score:" + score);
    }

    /* ================== private instance variables ================== */

    /**
     * A paddle in the bottom of the game which can be moved left and right by
     * mouse dragging
     */
    private GRect paddle;

    /**
     * A ball starting in the middle of the game. It will move straight until it
     * collide something and then bounce
     */
    private GOval ball;

    /** Number of bricks */
    private int numBricks;

    /** Mouse's last X location */
    private double mouseLastX;

    /** X and Y Velocities of the ball */
    private double vx, vy;

    /** Score for the game */
    private int score;

    /** Some prompt information */
    private GLabel prompt, scoreInfo;

    /** Private random generator */
    private RandomGenerator rgen = RandomGenerator.getInstance();

    /** Audio effect provider */
    private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

}
