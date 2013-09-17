/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {
    private static final int BOX_WIDTH = 130;

    private static final int BOX_HEIGHT = 40;

    private static final int HORIZONTAL_INTERVAL = 20;

    private static final int VERTICAL_INTERVAL = 40;

    /* main method */
    public static void main(String[] args) {
        new ProgramHierarchy().start(args);
    }

    public void run() {

        // Box Program
        int boxProgramX = (getWidth() - BOX_WIDTH) / 2;
        int boxProgramY =
                (getHeight() - BOX_HEIGHT * 2 - VERTICAL_INTERVAL) / 2;
        GRect boxProgram =
                new GRect(boxProgramX, boxProgramY, BOX_WIDTH, BOX_HEIGHT);
        add(boxProgram);

        // Label Program
        GLabel labelProgram = new GLabel("Program");
        int labelProgramX =
                (int) (boxProgramX * 2 + BOX_WIDTH - labelProgram.getWidth()) / 2;
        int labelProgramY =
                (int) (boxProgramY * 2 + BOX_HEIGHT + labelProgram.getAscent()) / 2;
        labelProgram.setLocation(labelProgramX, labelProgramY);
        add(labelProgram);

        // Box GraphicsProgram
        int boxGraphicsProgramX = boxProgramX - BOX_WIDTH - HORIZONTAL_INTERVAL;
        int boxGraphicsProgramY = boxProgramY + BOX_HEIGHT + VERTICAL_INTERVAL;
        GRect boxGraphicsProgram =
                new GRect(boxGraphicsProgramX, boxGraphicsProgramY, BOX_WIDTH,
                        BOX_HEIGHT);
        add(boxGraphicsProgram);

        // Line GraphicsProgram
        GLine lineGraphicsProgram =
                new GLine(boxProgramX + BOX_WIDTH / 2,
                        boxProgramY + BOX_HEIGHT, boxGraphicsProgramX
                                + BOX_WIDTH / 2, boxGraphicsProgramY);
        add(lineGraphicsProgram);

        // Label GraphicsProgram
        GLabel labelGraghicsProgram = new GLabel("GraphicsProgram");
        int labelGraghicsProgramX =
                (int) (boxGraphicsProgramX * 2 + BOX_WIDTH - labelGraghicsProgram
                        .getWidth()) / 2;
        int labelGraghicsProgramY =
                (int) (boxGraphicsProgramY * 2 + BOX_HEIGHT + labelGraghicsProgram
                        .getAscent()) / 2;
        labelGraghicsProgram.setLocation(labelGraghicsProgramX,
                labelGraghicsProgramY);
        add(labelGraghicsProgram);

        // Box ConsoleProgram
        int boxConsoleProgramX = boxProgramX;
        int boxConsoleProgramY = boxProgramY + BOX_HEIGHT + VERTICAL_INTERVAL;
        GRect boxConsoleProgram =
                new GRect(boxConsoleProgramX, boxConsoleProgramY, BOX_WIDTH,
                        BOX_HEIGHT);
        add(boxConsoleProgram);

        // Line ConsoleProgram
        GLine lineConsoleProgram =
                new GLine(boxProgramX + BOX_WIDTH / 2,
                        boxProgramY + BOX_HEIGHT, boxConsoleProgramX
                                + BOX_WIDTH / 2, boxConsoleProgramY);
        add(lineConsoleProgram);

        // Label ConsoleProgram
        GLabel labelConsoleProgram = new GLabel("ConsoleProgram");
        int labelConsoleProgramX =
                (int) (boxConsoleProgramX * 2 + BOX_WIDTH - labelConsoleProgram
                        .getWidth()) / 2;
        int labelConsoleProgramY =
                (int) (boxConsoleProgramY * 2 + BOX_HEIGHT + labelConsoleProgram
                        .getAscent()) / 2;
        labelConsoleProgram.setLocation(labelConsoleProgramX,
                labelConsoleProgramY);
        add(labelConsoleProgram);

        // Box DialogProgram
        int boxDialogProgramX = boxProgramX + BOX_WIDTH + HORIZONTAL_INTERVAL;
        int boxDialogProgramY = boxProgramY + BOX_HEIGHT + VERTICAL_INTERVAL;
        GRect boxDialogProgram =
                new GRect(boxDialogProgramX, boxDialogProgramY, BOX_WIDTH,
                        BOX_HEIGHT);
        add(boxDialogProgram);

        // Line DialogProgram
        GLine lineDialogProgram =
                new GLine(boxProgramX + BOX_WIDTH / 2,
                        boxProgramY + BOX_HEIGHT, boxDialogProgramX + BOX_WIDTH
                                / 2, boxDialogProgramY);
        add(lineDialogProgram);

        // Label DialogProgram
        GLabel labelDialogProgram = new GLabel("DialogProgram");
        int labelDialogProgramX =
                (int) (boxDialogProgramX * 2 + BOX_WIDTH - labelDialogProgram
                        .getWidth()) / 2;
        int labelDialogProgramY =
                (int) (boxDialogProgramY * 2 + BOX_HEIGHT + labelDialogProgram
                        .getAscent()) / 2;
        labelDialogProgram
                .setLocation(labelDialogProgramX, labelDialogProgramY);
        add(labelDialogProgram);

    }

}
