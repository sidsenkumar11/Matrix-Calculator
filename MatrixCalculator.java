/**
 * Calculates various operations on matrices and vectors
 *
 * @author Siddarth Senthilkumar
 * @version 2.0
 */
public class MatrixCalculator {

	/**
	 * ---------------------------------------------------------------------------
	 * FACTORIZATIONS FOR PART 1:
	 * 		LU_FACT
	 *		QR_FACT_HOUSH
	 *		QR_FACT_GIVENS
	 * ---------------------------------------------------------------------------
	 */

	/**
	 * Computes the LU factorization of a square matrix
	 * Algorithm learned from http://rosettacode.org/wiki/LU_decomposition
	 * @param matrix The nxn matrix to be factored
	 * @return The matrices and error
	 */
	public static Matrix[] lu_fact(Matrix a) {
		return Factorizations.lu_fact(a);
	}
	
	/**
	 * Computes a QR-factorization for Matrix a using HouseHolder reflections.
	 * @param a Matrix to factor
	 * @return the matrices for Q and R
	 */
	public static Matrix[] qr_fact_househ(Matrix a) {
		return Factorizations.qr_fact_househ(a);
	}

	/**
	 * Computes a QR-factorization for Matrix a using Givens rotations.
	 * @param a Matrix to factor
	 * @return the matrices for Q and R
	 */
	 public static Matrix[] qr_fact_givens(Matrix a) {
		return Factorizations.qr_fact_givens(a);
	 }

	/**
	 * ------------------------------------------------------
	 * SOLVING A MATRIX SYSTEM GIVEN A VECTOR B USING:
	 * 		LU FACTORIZATION
	 *		QR FACTORIZATION
	 * ------------------------------------------------------
	 */

	/**
	 * Uses LU factored matrix to solve for vector x.
	 * Assumes there is a solution.
	 * @param l The lower triangular matrix
	 * @param u The upper triangular matrix
	 * @param b The vector b in Ax = b
	 * @return The solution vector
	 */
	public static Vector solve_lu_b(Matrix l, Matrix u, Vector b) {
		return Factorizations.solve_lu_b(l, u, b);
	}

	/**
	 * Uses QR factored matrix to solve for vector x.
	 * Assumes there is a solution.
	 * @param q The orthogonal matrix
	 * @param r The upper triangular matrix
	 * @param b The vector b in Ax = b
	 * @return The solution vector
	 */
	public static Vector solve_qr_b(Matrix q, Matrix r, Vector b) {
		return Factorizations.solve_qr_b(q, r, b);
	}

	/**
	 * ------------------------------------------------------------
	 * POWER METHOD USED FOR PART 3 OF PROJECT - LESLIE MATRIX
	 * ------------------------------------------------------------
	 */

	/**
	 * Implements power method to find approximation
	 * of largest eigenvalue and corresponding eigenvector
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	public static PowerObject power_method(Matrix a, double tol, Vector u) {
		return PowerObject.power_method(a, tol, u);
	}

	/**
	 * -----------------------------------------------------------------
	 * MISCELLANEOUS OPERATIONS:
	 *		add(Matrix, Matrix)
	 *		subtract(Matrix, Matrix)
	 *		multiply(Matrix, Matrix)
	 *		multiply(Matrix, scalar)
	 *
	 *		add(Vector, Vector)
	 *		subtract(Vector, Vector)
	 *		multiply(Vector, scalar)
	 *		dotProduct(Vector, Vector)
	 *
	 *		multiply(Matrix, Vector)
	 *		multiply(Vector, Matrix)
	 *
	 * -----------------------------------------------------------------
	 */

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
		double sumValue;
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
	 * @throws IllegalArgumentException if the given
	 *		   matrices have different dimensions
	 */
	public static Matrix subtract(Matrix a, Matrix b) {

		if (a.rows() != b.rows() || a.columns() != b.columns()) {
			throw new IllegalArgumentException("Rows and columns are not equal");
		}

		Matrix difference = new Matrix(a.rows(), a.columns());
		double differenceValue;
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
		double sum;
		double[] row;
		double[] column;
		for (int rowA = 0; rowA < a.rows(); rowA++) {
			sum = 0;
			row = a.row(rowA);
			for (int colB = 0; colB < b.columns(); colB++) {
				sum = 0;
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
	 * Multiplies a matrix by a scalar
	 * @param a The matrix
	 * @param scalar The constant to multiply by
	 * @return The matrix containing the product
	 */
	public static Matrix multiply(Matrix a, double scalar) {
		Matrix product = new Matrix(a.rows(), a.columns());
		for (int i = 0; i < a.rows(); i++) {
			for (int j = 0; j < a.columns(); j++) {
				product.set(i, j, scalar * a.get(i, j));
			}
		}
		return product;
	}

	/**
	 * Adds 2 vectors together.
	 * @param u Vector to add
	 * @param v Vector to add
	 * @return resulting vector of adding u and v together
	 */
	 public static Vector add(Vector u, Vector v) {
		Vector sum = new Vector(u.rows());
		double sumValue;
		for (int row = 0; row < u.rows(); row++) {
			sumValue = u.get(row) + v.get(row);
			sum.set(row, sumValue);
		}
		return sum;
	}

	/**
	 * Subtracts two vectors
	 * @param a The first vector
	 * @param b The second vector
	 * @return The difference vector
	 * @throws IllegalArgumentException Vectors' lengths do not match
	 */
	public static Vector subtract(Vector a, Vector b) {
		if (a.numElements() != b.numElements()) {
			throw new IllegalArgumentException("Vectors' number of elements are not equal");
		}

		Vector x = new Vector(a.numElements());
		for (int i = 0; i < a.numElements(); i++) {
			x.set(i, a.get(i) - b.get(i));
		}
		return x;
	}

	/**
	 * Multiplies a vector by a scalar
	 * @param a The vector
	 * @param scalar The constant to multiply by
	 * @return The vector containing the product
	 */
	public static Vector multiply(Vector a, double scalar) {
		Vector product = new Vector(a.numElements());
		for (int i = 0; i < a.numElements(); i++) {
			product.set(i, scalar * a.get(i));
		}
		return product;
	}

	/**
	 * Multiplies a matrix by a vector
	 * @param a The matrix
	 * @param x The vector
	 * @return The vector containing the product
	 * @throws IllegalArgumentException if the given
	 *		   matrix's and vector's dimensions cannot be multiplied
	 */
	public static Vector multiply(Matrix a, Vector x) {
		if (a.columns() != x.rows()) {
			throw new IllegalArgumentException("A's columns must be the same as B's rows");
		}
		Vector product = new Vector(a.rows());
		double sum;
		double[] row;
		double[] column;
		for (int rowA = 0; rowA < a.rows(); rowA++) {
			sum = 0;
			row = a.row(rowA);
			for (int i = 0; i < row.length; i++) {
				sum += row[i] * x.get(i);
			}
			product.set(rowA, sum);
		}
		return product;
	}

	/**
	 * Multiplies a vector by a matrix.
	 * @param u The vector
	 * @param uTranspose The matrix
	 * @return The matrix containing the product between a vector and matrix
	 */
	public static Matrix multiply(Vector u, Matrix uTranspose) {
		Matrix vector = new Matrix(u);
		return multiply(vector, uTranspose);
	}

	/**
	 * Calculates the dot product of two vectors.
	 * @param one The first vector
	 * @param two The second vector
	 * @return The dot product of the two vectors
	 * @throws IllegalArgumentException if the two vectors are not the same length
	 */
	public static double dotProduct(Vector one, Vector two) throws IllegalArgumentException {
		if (one.rows() != two.rows()) {
			throw new IllegalArgumentException("Vectors not same length");
		}
		double sum = 0;
		for (int i = 0; i < one.rows(); i++) {
			sum += one.get(i) * two.get(i);
		}
		return sum;
	}
}