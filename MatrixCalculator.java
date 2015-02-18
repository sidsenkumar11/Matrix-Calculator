/**
 * Calculates various operations on matrices
 * and vectors.
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class MatrixCalculator {

	private Matrix a;
	private Matrix b;

	/**
	 * Creates matrix A with m rows and n columns.
	 * Creates matrix B with 0 rows and 0 columns.
	 */
	public MatrixCalculator(int m, int n) {
		a = new Matrix(m, n);
		b = new Matrix(m, n);
	}

	/**
	 * Creates matrix A with m rows and n columns.
	 * Creates matrix B with x rows and y columns.
	 */
	public MatrixCalculator(int m, int n, int x, int y) {
		a = new Matrix(m, n);
		b = new Matrix(x, y);
	}

	/**
	 * Adds two matrices' values together
	 * @param a The first matrix
	 * @param b The second matrix
	 * @return The matrix of containing the sum
	 * @throws IllegalOperandException if the given
	 *		   matrices have different dimensions
	 */
	public Matrix add(Matrix a, Matrix b) throws IllegalOperandException {

		if (a.rows() != b.rows() || a.columns() != b.columns()) {
			throw new IllegalOperandException("Rows and columns are not equal");
		}

		Matrix sum = new Matrix(a.rows(), a.columns());
		double sumValue = 0;
		for (int row = 0; row < a.rows(); row++) {
			for (int column = 0; column < a.columns(); column++) {
				sumValue = a.get(row, column) + b.get(row, column);
				sum.set(row, column, sumValue);
			}
		}
		return sum;
	}

	/**
	 * Subtracts two matrices' values
	 * @param a The first matrix
	 * @param b The second matrix
	 * @return The matrix of containing the difference
	 * @throws IllegalOperandException if the given
	 *		   matrices have different dimensions
	 */
	public Matrix subtract(Matrix a, Matrix b) throws IllegalOperandException {

		if (a.rows() != b.rows() || a.columns() != b.columns()) {
			throw new IllegalOperandException("Rows and columns are not equal");
		}

		Matrix difference = new Matrix(a.rows(), a.columns());
		double differenceValue = 0;
		for (int row = 0; row < a.rows(); row++) {
			for (int column = 0; column < a.columns(); column++) {
				differenceValue = a.get(row, column) - b.get(row, column);
				difference.set(row, column, differenceValue);
			}
		}
		return difference;
	}

	/**
	 * Multiplies two matrices
	 * @param a The first matrix
	 * @param b The second matrix
	 * @return The matrix containing the product
	 * @throws IllegalOperandException if the given
	 *		   matrices' dimensions cannot be multiplied
	 */
	public Matrix multiply(Matrix a, Matrix b) throws IllegalOperandException {
		/*
			For two matrices to be multiplied, if
			a is m x n, b must be n x y where y
			is a positive integer.		
		*/

		if (a.columns() != b.rows()) {
			throw new IllegalOperandException("A's columns must be the same as B's rows");
		}

		Matrix product = new Matrix(a.rows(), b.columns());
		double sum;
		double[] row;
		double[] column;
		for (int rowA = 0; rowA < a.rows(); rowA++) {
			sum = 0;
			row = a.row(rowA);
			for (int colB = 0; colB < b.columns(); colB++) {
				 column = b.column(colB);
				 for (int i = 0; i < row.length; i++) {
				 	sum += row[i] * column[i];
				 }
				 product.set(rowA, colB, sum);
			}
		}

		return product;
	}

	/**
	 * Row reduces the given matrix to echelon form.
	 * @param matrix The matrix to row reduce
	 * @return The echelon form of the matrix
	 */
	public Matrix rowReduce(Matrix matrix) {
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
	public Matrix getMatrixA() {
		return a;
	}

	/**
	 * Returns the matrix B
	 * @return The matrix B
	 */
	public Matrix getMatrixB() {
		return b;
	}
}