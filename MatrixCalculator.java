/**
 * Calculates various operations on matrices
 * and vectors.
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class MatrixCalculator {
	
	private double[][] matrixA;
	private double[][] matrixB;

	/**
	 * Creates matrix A with m rows and n columns.
	 * Creates matrix B with 0 rows and 0 columns.
	 */
	public MatrixCalculator(int m, int n) {
		matrixA = new double[m][n];
		matrixB = new double[0][0];
	}

	/**
	 * Creates matrix A with m rows and n columns.
	 * Creates matrix B with x rows and y columns.
	 */
	public MatrixCalculator(int m, int n, int x, int y) {
		matrixA = new double[m][n];
		matrixB = new double[x][y];
	}

	/**
	 * Row reduces the given matrix to echelon form.
	 * @param matrix The matrix to row reduce
	 * @return The echelon form of the matrix
	 */
	public double[][] rowReduce(double[][] matrix) {
		/*
			1) Begin with the leftmost nonzero column. This is a pivot column. The pivot position is at the top.
			2) Select a nonzero entry in the pivot column as a pivot.
			   If necessary, interchange rows to move this entry into the pivot position.
			3) Use row addition operations to create zeros in all positions below the pivot.
			4) Cover (or ignore) the row containing the pivot position and cover all rows, if any, above it.
			   Apply steps 1-3 to the submatrix that remains.
			   Repeat the process until there are no more nonzero rows to modify.
		*/

		return matrix;
	}

	/**
	 * Returns the matrix A
	 * @return The matrix A
	 */
	public double[][] getMatrixA() {
		return matrixA;
	}

	/**
	 * Returns the matrix B
	 * @return The matrix B
	 */
	public double[][] getMatrixB() {
		return matrixB;
	}
}