/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas implements NameSurferConstants,
        ComponentListener {

    private ArrayList<NameSurferEntry> entryList;

    /**
    * Creates a new NameSurferGraph object that displays the data.
    */
    public NameSurferGraph() {
        addComponentListener(this);
        entryList = new ArrayList<NameSurferEntry>();
        addGrid();
    }

    /**
    * Clears the list of name surfer entries stored inside this class.
    */
    public void clear() {
        entryList.clear();
        update();
    }

    /* Method: addEntry(entry) */
    /**
    * Adds a new NameSurferEntry to the list of entries on the display.
    * Note that this method does not actually draw the graph, but
    * simply stores the entry; the graph is drawn by calling update.
    */
    public void addEntry(NameSurferEntry entry) {
        if (entry != null) {
            entryList.add(entry);
            update();
        }
    }

    /**
    * Updates the display image by deleting all the graphical objects
    * from the canvas and then reassembling the display according to
    * the list of entries. Your application must call update after
    * calling either clear or addEntry; update is also called whenever
    * the size of the canvas changes.
    */
    public void update() {
        removeAll();
        addGrid();
    }

    private void addGrid() {
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),
                getHeight() - GRAPH_MARGIN_SIZE));

        for (int i = 0; i < NDECADES; i++) {
            add(new GLine(getWidth() / NDECADES * i, 0, getWidth() / NDECADES
                    * i, getHeight()));
            add(new GLabel("" + (START_DECADE + i*10), getWidth() / NDECADES * i
                    + 5, getHeight() - 5));
        }

        for (int i = 0; i < entryList.size(); i++) {
            NameSurferEntry entry = entryList.get(i);
            double preX = 0;
            double preY = 0;
            for (int j = 0; j < NDECADES; j++) {
                int rank = entry.getRank(j);
                double curY, curX = getWidth() / NDECADES * j;
                if (rank == 0)
                    curY = getHeight() - GRAPH_MARGIN_SIZE;
                else
                    curY =
                            (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank / 1000
                                    + GRAPH_MARGIN_SIZE;
                if (j > 0) {
                    GLine line = new GLine(preX, preY, curX, curY);
                    line.setColor(lineColor(i));
                    add(line);
                }
                String record;
                if (rank == 0)
                    record = entry.getName() + " *";
                else
                    record = entry.getName() + " " + rank;
                GLabel label = new GLabel(record, curX, curY);
                label.setColor(lineColor(i));
                add(label);

                preX = curX;
                preY = curY;
            }
        }
    }

    private Color lineColor(int i) {
        switch (i % 4) {
        case 0:
            return Color.black;
        case 1:
            return Color.red;
        case 2:
            return Color.blue;
        case 3:
            return Color.magenta;

        default:
            return null;
        }
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
