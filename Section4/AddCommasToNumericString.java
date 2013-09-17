import acm.program.*;

public class AddCommasToNumericString extends ConsoleProgram {
    /* main method */
    public static void main(String[] args) {
        new AddCommasToNumericString().start(args);
    }

    public void run() {
        while (true) {
            String digits = readLine("Enter a numeric string: ");
            if (digits.length() == 0)
                break;
            println(addCommasToNumericString(digits));
        }
    }

    private String addCommasToNumericString(String digits) {
        String result = "";
        int len = digits.length();
        for (int i = 0; i < len; i++) {
            result = digits.charAt(len - i - 1) + result;
            if ((((i + 1) % 3) == 0) && (len - 1 - i > 0)) {
                result = "," + result;
            }
        }
        return result;
    }

}
