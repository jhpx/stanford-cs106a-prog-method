import acm.program.*;

public class RemoveAllOccurrences extends ConsoleProgram {
    /* main method */
    public static void main(String[] args) {
        new RemoveAllOccurrences().start(args);
    }

    public void run() {
        while (true) {
            String str = readLine("Please Enter a String:");
            String substr = readLine("Please Enter a subString:");
            if (str.length() == 0)
                break;
            println(removeAllOccurrences(str, substr));
        }

    }

    private String removeAllOccurrences(String str, String substr) {
        String result = "";
        String tmp_result = "";
        for (int i = 0; i < str.length(); i++) {
            tmp_result = "" + str.charAt(i);
            for (int j = 0; j < substr.length(); j++) {
                char ch = substr.charAt(j);
                if (str.charAt(i) != ch) {
                    result += tmp_result;
                    tmp_result = "";
                    break;
                } else if (i < str.length() - 1 && j < substr.length() - 1) {
                    i++;
                    tmp_result += str.charAt(i);
                }
            }
        }
        return result;
    }
}
