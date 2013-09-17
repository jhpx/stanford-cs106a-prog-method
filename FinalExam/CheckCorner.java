import acm.program.ConsoleProgram;

public class CheckCorner extends ConsoleProgram {

    /** main method */
    public static void main(String[] args) {
        new CheckCorner().start(args);

    }

    public void run() {
        int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        println("The answer is " + checkUpperLeftCorner(matrix) + ".");
    }

    /** Method: checkUpperLeftCorner 
     * 
     * This method checks the upper left corner of a Sudoku array 
     * to see if it correctly contains one copy of each digit 
     * between 1 and 9.  If so, the method returns true.  If it 
     * contains values that are duplicated or out of range, the 
     * method returns false. 
     */
    private boolean checkUpperLeftCorner(int[][] matrix) {
        boolean[] alreadyUsed = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int digit = matrix[i][j];
                if (digit < 1 || digit > 9)
                    return false;
                if (alreadyUsed[digit])
                    return false;
                alreadyUsed[digit] = true;
            }
        }
        return true;
    }

}
