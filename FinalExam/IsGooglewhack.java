import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class IsGooglewhack extends ConsoleProgram {
    /** main method */
    public static void main(String[] args) {
        new IsGooglewhack().start(args);

    }

    public void run() {
        String str1 = "map";
        String str2 = "hash";
        println("The answer is " + isGooglewhack(str1, str2) + ".");
    }

    /** Method: isGooglewhack(word1, word2) 
     * 
     * Returns true if word1 and word2 appear on exactly one web page,  
     * as reported by googleSearch. 
     */
    private boolean isGooglewhack(String word1, String word2) {
        String[] pages1 = googleSearch(word1);
        String[] pages2 = googleSearch(word2);
        int matches = 0;
        for (int i = 0; i < pages1.length; i++) {
            if (findStringInArray(pages1[i], pages2) != -1) {
                matches++;
                if (matches > 1)
                    return false;
            }
        }
        return (matches == 1);
    }

    /** Method: findStringInArray(key, array) 
     * 
     * Returns the index of the first occurrence of key in the array. 
     * If key does not appear in the array, findStringInArray 
     * returns -1. 
     */
    private int findStringInArray(String key, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (key.equals(array[i]))
                return i;
        }
        return -1;
    }

    private String[] googleSearch(String s) {
        RandomGenerator rgen = new RandomGenerator();
        int N = rgen.nextInt();

        String[] page = new String[N];

        for (int i = 0; i < page.length; i++) {
            int x = rgen.nextInt();
            page[i] = s + x;
        }

        return page;
    }
}
