import java.util.HashMap;
import java.util.Iterator;

import acm.program.ConsoleProgram;

public class CommonKeyValuePairs extends ConsoleProgram {

    /** main method */
    public static void main(String[] args) {
        new CommonKeyValuePairs().start(args);

    }

    public void run() {
        HashMap<String, String> map1 = new HashMap<String, String>();
        HashMap<String, String> map2 = new HashMap<String, String>();
        map1.put("1", "A");
        map1.put("2", "B");
        map1.put("4", "A");
        map2.put("1", "A");
        map2.put("2", "B");
        map2.put("3", "C");
        map2.put("4", "D");

        println("The answer is " + commonKeyValuePairs(map1, map2) + ".");
    }

    /** Method: commonKeyValuePairs(map1, map2) 
     * 
     * Returns a count of the number of common key/value pairs in the 
     * two HashMaps that are passed in. 
     */
    public int commonKeyValuePairs(HashMap<String, String> map1,
            HashMap<String, String> map2) {

        int count = 0;

        // Get iterator over map1
        Iterator<String> it = map1.keySet().iterator();

        while (it.hasNext()) {
            // Get key from map1
            String key = it.next();

            // See if that keys exists in map2
            if (map2.containsKey(key)) {
                // Look up values associated with key in both maps
                String map1Value = map1.get(key);
                String map2Value = map2.get(key);

                // See if values are equal
                if (map2Value.equals(map1Value)) {
                    count++;
                }
            }
        }

        return count;
    }

}
