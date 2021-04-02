public class SetMatrixZeros {
/*    Given a m * n matrix, if an element is 0, set its entire row and column to 0.
    Do it in place.

    Analysis

    This problem should be solved in place, i.e., no other array should be used. We can use the first column and the first row to track if a row/column should be set to 0.

    Since we used the first row and first column to mark the zero row/column, the original values are changed.


            Specifically, given, the following matrix
    set-matrix-zero-1
            this problem can be solved by following 4 steps:

    Step 1:
    First row contains zero = true;
    First column contains zero = false;

    Step 2: use first row and column to mark zero row and column.
            set-matrix-zero-2

    Step 3: set each elements by using marks in first row and column.
            set-matrix-zero-3

    Step 4: Set first column and row by using marks in step 1.
    set-matrix-zero-4*/

    //https://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/

    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;

        //set first row and column zero or not
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowZero = true;
                break;
            }
        }

        //mark zeros on first row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //use mark to set elements
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //set first column and row
        if (firstColumnZero) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }

        if (firstRowZero) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }

    }
}
