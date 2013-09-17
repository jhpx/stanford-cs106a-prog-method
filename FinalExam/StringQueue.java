/* 
 * File: StringQueue.java 
 * ---------------------- 
 * This program implements the MinimalStringQueue interface using 
 * an ArrayList for internal storage. 
 */

import java.util.*;

/** Implements an ArrayList queue */
public class StringQueue implements MinimalStringQueue {

    /** Creates a new empty queue. */
    public StringQueue() {
        waitingLine = new ArrayList<String>();
    }

    /** Adds a new String to the end of the queue */
    public void add(String str) {
        waitingLine.add(str);
    }

    /** Removes and returns the first String (or null if queue is empty) */
    public String poll() {
        if (waitingLine.isEmpty())
            return null;
        String first = waitingLine.get(0);
        waitingLine.remove(0);
        return first;
    }

    /** Returns the number of entries in the queue. */
    public int size() {
        return waitingLine.size();
    }

    /* Private instance variables */
    private ArrayList<String> waitingLine;

}