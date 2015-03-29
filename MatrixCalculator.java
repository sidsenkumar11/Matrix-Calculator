import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates various operations on matrices and vectors
 * @author Siddarth Senthilkumar
 * @version 2.0
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
	 * Adds 2 vectors together.
	 * @param u Vector to add
	 * @param v Vector to add
	 * @return resulting vector of adding u and v together
	 */
	 public static Vector add(Vector u, Vector v) {
		Vector sum = new Vector(u.rows());
		BigDecimal sumValue;
		for (int row = 0; row < u.rows(); row++) {
			sumValue = u.get(row).add(v.get(row));
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
			x.set(i, a.get(i).subtract(b.get(i)));
		}
		return x;
	}

	/**
	 * Multiplies a vector by a scalar
	 * @param a The vector
	 * @param scalar The constant to multiply by
	 * @return The vector containing the product
	 */
	public static Vector multiply(Vector a, BigDecimal scalar) {
		Vector product = new Vector(a.numElements());
		for (int i = 0; i < a.numElements(); i++) {
			product.set(i, new BigDecimal("" + scalar).multiply(a.get(i)));
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
		BigDecimal sum;
		BigDecimal[] row;
		BigDecimal[] column;
		for (int rowA = 0; rowA < a.rows(); rowA++) {
			sum = BigDecimal.ZERO;
			row = a.row(rowA);
			for (int i = 0; i < row.length; i++) {
				sum = sum.add(row[i].multiply(x.get(i)));
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
	 * ---------------------------------------------------------------------------
	 * FACTORIZATIONS:
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
		Vector x = new Vector(l.columns());
		Vector y = new Vector(l.columns());

		// Ly = b
		// Solve for y. L is in Lower triangular with ones along diagonal
		// y(n) = (b(n) - sum of products) / l(n)
		// where
		// sum of products = for (i = 0; i < n; i++) sum += l(i) * y(i)

		for (int i = 0; i < y.numElements(); i++) {
			BigDecimal sumOfProducts = BigDecimal.ZERO;
			for (int j = 0; j < i; j++) {
				sumOfProducts = sumOfProducts.add(l.get(i, j).multiply(y.get(j)));
			}
			// BigDecimal value = (b.get(i) - sumOfProducts).divide(l.get(i, i));
			BigDecimal value = (b.get(i).subtract(sumOfProducts));
			y.set(i, value);
		}

		// Ux = Y
		// Solve for x, U is upper triangular
		for (int i = y.numElements() - 1; i >= 0; i--) {
			BigDecimal sumOfProducts = BigDecimal.ZERO;
			for (int j = u.rows() - 1; j > i; j--) {
				sumOfProducts = sumOfProducts.add(u.get(i, j).multiply(x.get(j)));
			}
			BigDecimal value = ((y.get(i).subtract(sumOfProducts))).divide(u.get(i, i), 10, RoundingMode.HALF_UP);
			x.set(i, value);
		}
		return x;
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
		Vector x = new Vector(q.columns());
		Vector d = multiply(q.transpose(), b);

		for (int i = d.numElements() - 1; i >= 0; i--) {
			BigDecimal sumOfProducts = BigDecimal.ZERO;
			for (int j = r.rows() - 1; j > i; j--) {
				sumOfProducts = sumOfProducts.add(r.get(i, j).multiply(x.get(j)));
			}
			BigDecimal value = ((d.get(i).subtract(sumOfProducts))).divide(r.get(i, i), 10, RoundingMode.HALF_UP);
			x.set(i, value);
		}
		return x;
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
	public static PowerObject power_method(Matrix a, BigDecimal tol, Vector u) {

		// Get largest value from u - initial eigenvalue guess
		BigDecimal largest = u.get(0);
		for (int i = 1; i < u.numElements(); i++) {
			if (u.get(i).compareTo(largest) > 0) {
				largest = u.get(i);
			}
		}

		BigDecimal previous = largest;

		// Factor out that value from u
		u = MatrixCalculator.multiply(u, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));
		PowerObject powerInfo = new PowerObject(largest, u, 0, true);

		// Do one iteration

		// Get Au
		Vector au = multiply(a, u);

		// Get largest in Au
		largest = au.get(0);
		for (int i = 1; i < au.numElements(); i++) {
			if (au.get(i).compareTo(largest) > 0) {
				largest = au.get(i);
			}
		}

		// u = Au / ||largest in Au||
		u = multiply(au, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));

		// Update values in powerInfo
		powerInfo.setEigenvalue(largest);
		powerInfo.setEigenvector(u);
		powerInfo.incrementIterations();

		// Keep updating powerInfo and u until tolerance is fine
		u = power_method(a, tol, u, previous, powerInfo);
		return powerInfo;
	}

	/**
	 * Recursively calculates new eigenvector with power method
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @param prev The previous eigenvalue
	 * @param powerInfo The PowerObject containing return data
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	private static Vector power_method(Matrix a, BigDecimal tol, Vector u, BigDecimal prev, PowerObject powerInfo) {

		BigDecimal thisPrevious = powerInfo.getEigenvalue();
		if (thisPrevious.abs().subtract(prev.abs()).abs().compareTo(tol) <= 0) {
			// Difference between current largest eigenvalue and previous eigenvalue is less than or equal to tolerance
			// We are done iterating.
			return u;
		}

		// Tolerance not enough. need another iteration
		Vector au = multiply(a, u);

		// Get au's largest
		BigDecimal largest = au.get(0);
		for (int i = 1; i < au.numElements(); i++) {
			if (au.get(i).compareTo(largest) > 0) {
				largest = au.get(i);
			}
		}

		// Divide au by its largest and set it to u
		u = multiply(au, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));

		// Update powerInfo
		powerInfo.setEigenvalue(largest);
		powerInfo.setEigenvector(u);
		powerInfo.incrementIterations();

		// Check again if tolerance is fine
		return power_method(a, tol, u, thisPrevious, powerInfo);
	}

	/**
	 * -----------------------------------------------------------------
	 * MISCELLANEOUS OPERATIONS:
	 *		SQRT - CALCULATES THE SQUARE ROOT OF A BIGDECIMAL
	 *
	 * -----------------------------------------------------------------
	 */

	/**
	 * Calculates the square root of a BigDecimal to precision 32 bits.
	 * @param value The square root to be calculated
	 * @return The BigDecimal containing the square root
	 */
	public static BigDecimal sqrt(BigDecimal value) {
    	BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
    	return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}
}