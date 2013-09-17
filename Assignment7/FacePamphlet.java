/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

    private JTextField nameField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton lookupButton;

    private JTextField changeStatusField;
    private JTextField changePictureField;
    private JTextField addFriendField;
    private JButton changeStatusButton;
    private JButton changePictureButton;
    private JButton addFriendButton;

    FacePamphletDatabase db;
    FacePamphletProfile current;
    FacePamphletCanvas canvas;

    /** main method */
    public static void main(String[] args) {
        new FacePamphlet().start(args);
    }

    /**
     * This method has the responsibility for initializing the 
     * interactors in the application, and taking care of any other 
     * initialization that needs to be performed.
     */
    public void init() {
        db = new FacePamphletDatabase();
        canvas = new FacePamphletCanvas();
        add(canvas);
        prepareButtons();
    }

    private void prepareButtons() {
        nameField = new JTextField(TEXT_FIELD_SIZE);
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        lookupButton = new JButton("Lookup");
        add(new JLabel("Name"), NORTH);
        add(nameField, NORTH);
        add(addButton, NORTH);
        add(deleteButton, NORTH);
        add(lookupButton, NORTH);

        changeStatusField = new JTextField(TEXT_FIELD_SIZE);
        changeStatusField.addActionListener(this);
        changePictureField = new JTextField(TEXT_FIELD_SIZE);
        changePictureField.addActionListener(this);
        addFriendField = new JTextField(TEXT_FIELD_SIZE);
        addFriendField.addActionListener(this);
        changeStatusButton = new JButton("Change Status");
        changePictureButton = new JButton("Change Picture");
        addFriendButton = new JButton("Add Friend");
        add(changeStatusField, WEST);
        add(changeStatusButton, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(changePictureField, WEST);
        add(changePictureButton, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(addFriendField, WEST);
        add(addFriendButton, WEST);
        addActionListeners();
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == addButton) {
            addProfile();
        } else if (source == deleteButton) {
            deleteProfile();
        } else if (source == lookupButton) {
            lookupProfile();
        } else if (source == changeStatusField || source == changeStatusButton) {
            changeStatus();
        } else if (source == changePictureField
                || source == changePictureButton) {
            changePicture();
        } else if (source == addFriendField || source == addFriendButton) {
            addFriend();

        }

    }

    private void addProfile() {
        String name = nameField.getText();
        FacePamphletProfile profile = db.getProfile(name);
        if (profile == null) {
            current = new FacePamphletProfile(name);
            db.addProfile(current);
            displayCurrentProfile();
            canvas.showMessage("New profile created");
        } else {
            current = profile;
            displayCurrentProfile();
            canvas.showMessage("A profile with the name <" + name
                    + "> already exists");
        }
    }

    private void deleteProfile() {
        String name = nameField.getText();
        FacePamphletProfile profile = db.getProfile(name);
        current = null;
        if (profile == null) {
            displayCurrentProfile();
            canvas.showMessage("Profile with the name <" + name
                    + "> does not exist");
        } else {
            db.deleteProfile(name);
            displayCurrentProfile();
            canvas.showMessage("Profile of <" + name + "> deleted");
        }
    }

    private void lookupProfile() {
        String name = nameField.getText();
        FacePamphletProfile profile = db.getProfile(name);
        current = profile;
        if (profile == null) {
            displayCurrentProfile();
            canvas.showMessage("A profile with the name <" + name
                    + "> does not exist");
        } else {
            displayCurrentProfile();
            canvas.showMessage("Displaying <" + name + ">");
        }
    }

    private void changeStatus() {
        String status = changeStatusField.getText();
        if (current == null) {
            displayCurrentProfile();
            canvas.showMessage("Please select a profile to change status");
        } else {
            current.setStatus(status);
            displayCurrentProfile();
            canvas.showMessage("Status updated to <" + status + ">");
        }
    }

    private void changePicture() {
        GImage image = null;
        try {
            image = new GImage(changePictureField.getText());
        } catch (Exception ex) {
            canvas.showMessage("Unable to open image file: <"
                    + changePictureField.getText() + ">");
            return;
        }

        if (current == null) {
            displayCurrentProfile();
            canvas.showMessage("Please select a profile to change picture");
        } else {
            current.setImage(image);
            displayCurrentProfile();
            canvas.showMessage("Picture updated");
        }
    }

    private void addFriend() {
        String friendName = addFriendField.getText();
        FacePamphletProfile friend = db.getProfile(friendName);
        if (current == null) {
            canvas.showMessage("Please select a profile to add friend");

        } else if (friend == null) {
            canvas.showMessage("<" + friendName + "> does not exist");

        } else {
            boolean result = current.addFriend(friendName);
            if (result) {
                displayCurrentProfile();
                canvas.showMessage("<" + friendName + "> added as a friend");
                friend.addFriend(current.getName());
            } else {
                displayCurrentProfile();
                canvas.showMessage("<" + current.getName() + "> already has <"
                        + friendName + "> as a friend");
            }
        }
    }

    private void displayCurrentProfile() {
        if (current == null) {
            canvas.removeAll();
        } else
            canvas.displayProfile(current);
    }
}
