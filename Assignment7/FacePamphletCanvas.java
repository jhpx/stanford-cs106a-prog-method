/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.util.Iterator;

public class FacePamphletCanvas extends GCanvas implements
        FacePamphletConstants {

    GLabel message;
    GLabel name;
    GImage image;
    GLabel status;
    GRect rectangle;
    GLabel noimage;
    GLabel friendList;

    /** 
     * Constructor
     * This method takes care of any initialization needed for 
     * the display
     */
    public FacePamphletCanvas() {
        name = new GLabel("");
        name.setColor(Color.BLUE);
        name.setFont(PROFILE_NAME_FONT);

        noimage = new GLabel("No Image");
        noimage.setFont(PROFILE_IMAGE_FONT);

        image = null;

        status = new GLabel("");
        status.setFont(PROFILE_STATUS_FONT);

        message = new GLabel("");
        message.setFont(MESSAGE_FONT);

        friendList = new GLabel("Friends:");
        friendList.setFont(PROFILE_FRIEND_LABEL_FONT);
    }

    /** 
     * This method displays a message string near the bottom of the 
     * canvas.  Every time this method is called, the previously 
     * displayed message (if any) is replaced by the new message text 
     * passed in.
     */
    public void showMessage(String msg) {
        message.setLabel(msg);
        message.setLocation(getWidth() / 2 - message.getWidth() / 2,
                getHeight() - BOTTOM_MESSAGE_MARGIN + message.getAscent() / 2);
        add(message);
    }

    /** 
     * This method displays the given profile on the canvas.  The 
     * canvas is first cleared of all existing items (including 
     * messages displayed near the bottom of the screen) and then the 
     * given profile is displayed.  The profile display includes the 
     * name of the user from the profile, the corresponding image 
     * (or an indication that an image does not exist), the status of
     * the user, and a list of the user's friends in the social network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        removeAll();

        double x = 0, y = 0;
        name.setLabel(profile.getName());
        x += LEFT_MARGIN;
        y += TOP_MARGIN + name.getAscent() / 2;
        name.setLocation(x, y);
        add(name);

        y += IMAGE_MARGIN;
        if (profile.getGImage() == null) {
            rectangle = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
            add(rectangle);

            noimage.setLocation(x + IMAGE_WIDTH / 2 - noimage.getWidth() / 2, y
                    + IMAGE_HEIGHT / 2 + noimage.getAscent() / 2);
            add(noimage);
        } else {
            Image img = profile.getGImage().getImage();
            image = new GImage(img);
            image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
            image.setLocation(x, y);
            add(image);
        }

        String stat = profile.getStatus();
        if (stat == null || stat.isEmpty())
            status.setLabel("No current status");
        else
            status.setLabel(profile.getName() + " is " + stat);
        y += IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent() / 2;
        status.setLocation(x, y);
        add(status);

        x = getWidth() / 2;
        y = TOP_MARGIN + IMAGE_MARGIN + friendList.getAscent() / 2;
        friendList.setLocation(x, y);
        add(friendList);

        Iterator<String> iter = profile.getFriends();
        while (iter.hasNext()) {
            GLabel f = new GLabel(iter.next());
            f.setFont(PROFILE_FRIEND_FONT);
            y += f.getAscent();
            add(f, x, y);

        }
    }
}
