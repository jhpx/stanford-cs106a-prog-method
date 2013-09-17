/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;

import acm.util.*;
import acmx.export.java.util.ArrayList;

public class HangmanLexicon {
	ArrayList lexicon;

	/** This is the HangmanLexicon constructor */
	public HangmanLexicon() {
		lexicon = new ArrayList();
		try {
			BufferedReader rd = new BufferedReader(new FileReader(
					"HangmanLexicon.txt"));

			while (true) {
				String str = rd.readLine();
				if (str == null)
					break;
				lexicon.add(str);
			}

		} catch (Exception e) {
			throw new ErrorException(e);
		}

	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return (String)lexicon.get(index);
	};
}
