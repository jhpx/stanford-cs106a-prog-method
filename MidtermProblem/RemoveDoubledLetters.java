/*
 *  File: RemoveDoubledLetters.java
 */
import acm.program.ConsoleProgram;

public class RemoveDoubledLetters extends ConsoleProgram {
    
    /* main method */
    public static void main(String[] args) {
        new RemoveDoubledLetters().start(args);
    }
    
	public void run() {
		while (true) {
			String s = readLine();
			if (s.equals(""))
				break;
			println(removeDoubledLetters(s));
		}
		exit();
	}

	private String removeDoubledLetters(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (i == 0 || ch != str.charAt(i - 1)) {
				result += ch;
			}
		}
		return result;
	}
}