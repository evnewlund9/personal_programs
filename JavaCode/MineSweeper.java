package recursion;

//Found at https://www.cs.utexas.edu/~scottm/cs307/javacode/codeSamples/MineSweeper.java

public class MineSweeper {

    private int[][] myTruth;
    private boolean[][] myShow;

    private void cellPicked(int row, int col) {
        if( inBounds(row, col) && !myShow[row][col]) {
            myShow[row][col] = true;

            if( myTruth[row][col] == 0) {
                for(int r = -1; r <= 1; r++)
                for(int c = -1; c <= 1; c++)
                cellPicked(row + r, col + c);
            }
        }
    }

    private boolean inBounds(int row, int col) {
        return 0 <= row && row < myTruth.length && 0 <= col && col < myTruth[0].length;
    }
}