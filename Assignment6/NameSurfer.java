/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferDataBase db;
    private NameSurferGraph graph;

    /* main method */
    public static void main(String[] args) {
        new NameSurfer().start(args);
    }

    /* Method: init() */
    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the bottom of the window.
     */
    public void init() {
        graph = new NameSurferGraph();
        add(graph);
        db = new NameSurferDataBase("names-data.txt");
        prepareButtons();
    }

    private void prepareButtons() {
        nameField = new JTextField(MAX_NAME);
        nameField.addActionListener(this);
        graphButton = new JButton("Graph");
        clearButton = new JButton("Clear");
        add(new JLabel("Name"), SOUTH);
        add(nameField, SOUTH);
        add(graphButton, SOUTH);
        add(clearButton, SOUTH);
        addActionListeners();
    }

    /* Method: actionPerformed(e) */
    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == graphButton || source == nameField) {
            graph.addEntry(db.findEntry(nameField.getText()));
        } else if (source == clearButton) {
            graph.clear();
        }
    }
}
