import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Calculates various operations on matrices and vectors
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class MatrixCalculator {

	/**
	 * Adds two matrices' values together
	 * @param a The first matrix
	 * @param b The second matrix
	 * @return The matrix of containing the sum
	 * @throws IllegalArgumentException if the given
	 *		   matrices have different dimensions
	 */
	public static Matrix add(Matrix a, Matrix b) {

		if (a.rows() != b.rows() || a.columns() != b.columns()) {
			throw new IllegalArgumentException("Rows and columns are not equal");
		}

		Matrix sum = new Matrix(a.rows(), a.columns());
		BigDecimal sumValue;
		for (int row = 0; row < a.rows(); row++) {
			for (int column = 0; column < a.columns(); column++) {
				sumValue = a.get(row, column).add(b.get(row, column));
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
	 * @throws IllegalArgumentException if the given
	 *		   matrices have different dimensions
	 */
	public static Matrix subtract(Matrix a, Matrix b) {

		if (a.rows() != b.rows() || a.columns() != b.columns()) {
			throw new IllegalArgumentException("Rows and columns are not equal");
		}

		Matrix difference = new Matrix(a.rows(), a.columns());
		BigDecimal differenceValue;
		for (int row = 0; row < a.rows(); row++) {
			for (int column = 0; column < a.columns(); column++) {
				differenceValue = a.get(row, column).subtract(b.get(row, column));
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
	 * @throws IllegalArgumentException if the given
	 *		   matrices' dimensions cannot be multiplied
	 */
	public static Matrix multiply(Matrix a, Matrix b) {
		/*
			For two matrices to be multiplied, if
			a is m x n, b must be n x y where y
			is a positive integer.		
		*/

		if (a.columns() != b.rows()) {
			throw new IllegalArgumentException("A's columns must be the same as B's rows");
		}

		Matrix product = new Matrix(a.rows(), b.columns());
		BigDecimal sum;
		BigDecimal[] row;
		BigDecimal[] column;
		for (int rowA = 0; rowA < a.rows(); rowA++) {
			sum = BigDecimal.ZERO;
			row = a.row(rowA);
			for (int colB = 0; colB < b.columns(); colB++) {
				sum = BigDecimal.ZERO;
				column = b.column(colB);
				for (int i = 0; i < row.length; i++) {
					sum = sum.add(row[i].multiply(column[i]));
				}
				product.set(rowA, colB, sum);
			}
		}
		return product;
	}

	/**
	 * Multiplies a matrix by a scalar
	 * @param a The matrix
	 * @param scalar The constant to multiply by
	 * @return The matrix containing the product
	 */
	public static Matrix multiply(Matrix a, BigDecimal scalar) {

		Matrix product = new Matrix(a.rows(), a.columns());

		for (int i = 0; i < a.rows(); i++) {
			for (int j = 0; j < a.columns(); j++) {
				product.set(i, j, new BigDecimal("" + scalar).multiply(a.get(i, j)));
			}
		}

		return product;
	}

	/**
	 * Calculates the dot product of two vectors.
	 * @param one The first vector
	 * @param two The second vector
	 * @return The dot product of the two vectors
	 * @throws IllegalArgumentException if the two vectors are not the same length
	 */
	public static BigDecimal dotProduct(Vector one, Vector two) throws IllegalArgumentException {
		if (one.rows() != two.rows()) {
			throw new IllegalArgumentException("Vectors not same length");
		}

		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < one.rows(); i++) {
			sum = sum.add(one.get(i).multiply(two.get(i)));
		}
		return sum;
	}

	/**
	 * Returns the transpose of the given matrix.
	 * @param matrix The original matrix
	 * @return The transpose of the original matrix
	 */
	public static Matrix transpose(Matrix matrix) {
		Matrix transpose = new Matrix(matrix.columns(), matrix.rows());
		int newRow = 0;
		int newColumn = 0;

		for (int row = 0; row < matrix.rows(); row++) {
			newRow = 0;
			for (int column = 0; column < matrix.columns(); column++) {
				transpose.set(newRow, newColumn, matrix.get(row, column));
				newRow++;
			}
			newColumn++;
		}

		return transpose;
	}

	/**
	 * Computes the LU factorization of a square matrix
	 * Algorithm learned from http://rosettacode.org/wiki/LU_decomposition
	 * @param matrix The nxn matrix to be factored
	 * @return The matrices and error
	 */
	public static Matrix[] lu_fact(Matrix a) {
		Matrix l = new Matrix(a.rows(), a.columns());
		Matrix u = new Matrix(a.rows(), a.columns());

		// Put 1s into L's diagonal so that there is a unique solution

		for (int i = 0; i < a.rows(); i++) {
			for (int j = 0; j < a.columns(); j++) {
				if (i == j) {
					l.set(i, j, BigDecimal.ONE);
				}
			}
		}

		// U's top row is the same as A's top row
		for (int i = 0; i < a.columns(); i++) {
			u.set(0, i, a.get(0, i));
		}

		// A's leftmost column = l(i, 0) * u(0, 0)
		// a(i, 0) = l(i, 0) * u(0, 0)
		// Therefore l(i, 0) = a(i, 0) / u(0, 0)

		for (int i = 1; i < a.rows(); i++) {
			BigDecimal value = a.get(i, 0).divide(u.get(0, 0));
			l.set(i, 0, value);
		}


		// We now have the first row for U and the first column for L
		// Apply formulas u(i, j) = a(i, j) - sum of k = 0 to i - 1 of u(k, j)*l(i, k)
		// and l(i, j) = 1 / u(j, j) * (a(i, j) - sum of k = 0 to j - 1 of u(k, j)*l(i, k))
		for (int i = 1; i < a.rows(); i++) {
			for (int j = 1; j < a.columns(); j++) {
				if (i <= j) {
					// Only U should be affected
					BigDecimal sum = BigDecimal.ZERO;
					for (int k = 0; k <= i - 1; k++) {
						sum = sum.add(u.get(k, j).multiply(l.get(i, k)));
					}
					u.set(i, j, a.get(i, j).subtract(sum));
				} else {
					// Lower Triangle, only L affected
					BigDecimal sum = BigDecimal.ZERO;
					for (int k = 0; k <= j - 1; k++) {
						sum = sum.add(u.get(k, j).multiply(l.get(i, k)));
					}
					MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
					l.set(i, j, (a.get(i, j).subtract(sum)).divide(u.get(j, j), mc));
				}
			}
		}

		Matrix[] matrices = {l, u};
		return matrices;
	}

	/**
	 * Row reduces the given matrix to echelon form.
	 * @param matrix The matrix to row reduce
	 * @return The echelon form of the matrix
	 */
	public static Matrix rowReduce(Matrix matrix) {
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
}