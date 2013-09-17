/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		setupHangman();
		add(hangman, (getWidth() - hangman.getWidth()) / 2,
				(getHeight() - hangman.getHeight()) / 2);

	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		targetWord.setLabel(word);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		switch (state) {
		case 0:
			hangman.add(head, BEAM_LENGTH - HEAD_RADIUS, ROPE_LENGTH);
			break;
		case 1:
			hangman.add(body);
			break;
		case 2:
			hangman.add(leftArm);
			break;
		case 3:
			hangman.add(rightArm);
			break;
		case 4:
			hangman.add(leftLeg);
			break;
		case 5:
			hangman.add(rightLeg);
			break;
		case 6:
			hangman.add(leftFoot);
			break;
		case 7:
			hangman.add(rightFoot);
			break;
		}
		state++;
		errorList.setLabel(errorList.getLabel() + letter);
	}

	/**
	 * Sets up all the parts of the hangman, including all the location relative
	 * to the hangman not the window
	 */
	private void setupHangmanParts() {
		scafflod = new GCompound();
		scafflod.add(new GLine(0, 0, 0, SCAFFOLD_HEIGHT));
		scafflod.add(new GLine(0, 0, BEAM_LENGTH, 0));
		scafflod.add(new GLine(BEAM_LENGTH, 0, BEAM_LENGTH, ROPE_LENGTH));

		head = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		body = new GLine(BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2,
				BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);

		leftArm = new GCompound();
		leftArm.add(new GLine(BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ ARM_OFFSET_FROM_HEAD, BEAM_LENGTH - UPPER_ARM_LENGTH,
				ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD));
		leftArm.add(new GLine(BEAM_LENGTH - UPPER_ARM_LENGTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, BEAM_LENGTH
				- UPPER_ARM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));

		rightArm = new GCompound();
		rightArm.add(new GLine(BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ ARM_OFFSET_FROM_HEAD, BEAM_LENGTH + UPPER_ARM_LENGTH,
				ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD));
		rightArm.add(new GLine(BEAM_LENGTH + UPPER_ARM_LENGTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD, BEAM_LENGTH
				+ UPPER_ARM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH));

		leftLeg = new GCompound();
		leftLeg.add(new GLine(BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ BODY_LENGTH, BEAM_LENGTH - HIP_WIDTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + BODY_LENGTH));
		leftLeg.add(new GLine(BEAM_LENGTH - HIP_WIDTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + BODY_LENGTH, BEAM_LENGTH - HIP_WIDTH,
				ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH));

		rightLeg = new GCompound();
		rightLeg.add(new GLine(BEAM_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ BODY_LENGTH, BEAM_LENGTH + HIP_WIDTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + BODY_LENGTH));
		rightLeg.add(new GLine(BEAM_LENGTH + HIP_WIDTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + BODY_LENGTH, BEAM_LENGTH + HIP_WIDTH,
				ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH));

		leftFoot = new GLine(BEAM_LENGTH - HIP_WIDTH, ROPE_LENGTH + HEAD_RADIUS
				* 2 + BODY_LENGTH + LEG_LENGTH, BEAM_LENGTH - HIP_WIDTH
				- FOOT_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH
				+ LEG_LENGTH);
		rightFoot = new GLine(BEAM_LENGTH + HIP_WIDTH, ROPE_LENGTH
				+ HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH, BEAM_LENGTH
				+ HIP_WIDTH + FOOT_LENGTH, ROPE_LENGTH + HEAD_RADIUS * 2
				+ BODY_LENGTH + LEG_LENGTH);

		targetWord = new GLabel(" ", 0, SCAFFOLD_HEIGHT + LABEL_OFFSET);
		targetWord.setFont(new Font("Times New Roman", Font.BOLD, 24));

		errorList = new GLabel(" ", 0, SCAFFOLD_HEIGHT + LABEL_OFFSET * 2);
	}

	/**
	 * Sets up the hangman
	 */
	private void setupHangman() {
		setupHangmanParts();
		hangman = new GCompound();
		hangman.add(scafflod);
		hangman.add(targetWord);
		hangman.add(errorList);

	}
	
	/* ================== private instance variables ================== */
	
	private static int state;

	private static GCompound hangman;
	private static GCompound scafflod;
	private static GOval head;
	private static GLine body;
	private static GCompound leftArm;
	private static GCompound rightArm;
	private static GCompound leftLeg;
	private static GCompound rightLeg;
	private static GLine leftFoot;
	private static GLine rightFoot;
	private static GLabel targetWord;
	private static GLabel errorList;

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int LABEL_OFFSET = 40;

}
