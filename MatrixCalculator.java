import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Calculates various operations on matrices and vectors
 * @author Siddarth Senthilkumar, Katherine Cabezas
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
	 * Multiplies 2 vectors together.
	 * @param u Vector to multiply
	 * @param v Vector to multiply
	 * @return resulting vector of multiplying u and v together
	 */
	 public static Vector multiply(Vector u, Vector v) {
		Vector product = new Vector(u.rows());
		BigDecimal productValue;
		for (int row = 0; row < u.rows(); row++) {
			productValue = u.get(row).multiply(v.get(row));
			product.set(row, productValue);
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
					l.set(i, j, (a.get(i, j).subtract(sum)).divide(u.get(j, j), 6, RoundingMode.HALF_UP));
				}
			}
		}

		Matrix[] matrices = {l, u};

		// System.out.println("||LU - A||: " + subtract(multiply(l, u), a).norm());
		return matrices;
	}

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
			MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
			BigDecimal value = ((y.get(i).subtract(sumOfProducts))).divide(u.get(i, i), mc);
			x.set(i, value);
		}

		return x;
	}
	
	// /**
	//  * Computes a QR-factorization for Matrix a using HouseHolder reflections.
	//  * @param a Matrix to factorize
	//  * @return the matrices for Q and R
	//  */
	// public static Matrix[] qr_fact_househ(Matrix a) {
	// 	/*
	// 	A = (H1*H2*...*Hn) * R
	// 	Q = H1*H2*...*Hn
	// 	R = (H1*H2*...*Hn) * A^-1
	// 	Householder reflection = I - 2uu^t
	// 	*/
		
	// 	Matrix i;
	// 	Matrix q;
	// 	Matrix r;
		
	// 	for (int i = 0; i < a.length; i++) {
	// 		Vector x = a.column(i);
	// 		Vector v = add(x, );
	// 		Vector u;
	// 	}
	// }
	
	// /**
	//  * Computes a QR-factorization for Matrix a using Givens rotations.
	//  * @param a Matrix to factorize
	//  * @return the matrices for Q and R
	//  */
	//  public static Matrix[] qr_fact_givens(Matrix a) {
	//  	/*
	// 	A = (G1*G2*...*Gn) * R
	// 	Q = G1*G2*...*Gn
	// 	R = (G1*G2*...*Gn) * A^-1
	// 	*/
	//  }

	/**
	 * Uses QR factored matrix to solve for vector x.
	 * Assumes there is a solution.
	 * @param q The orthonormal matrix
	 * @param r The upper triangular matrix
	 * @param b The vector b in Ax = b
	 * @return The solution vector
	 */
	public static Vector solve_qr_b(Matrix q, Matrix r, Vector b) {
		Vector x = new Vector(q.columns());
		Vector d = multiply(transpose(q), b);

		for (int i = d.numElements() - 1; i >= 0; i--) {
			BigDecimal sumOfProducts = BigDecimal.ZERO;
			for (int j = r.rows() - 1; j > i; j--) {
				sumOfProducts = sumOfProducts.add(r.get(i, j).multiply(x.get(j)));
			}
			MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
			BigDecimal value = ((d.get(i).subtract(sumOfProducts))).divide(r.get(i, i), mc);
			x.set(i, value);
		}
		return x;
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
	 * Implements power method to find approximation
	 * of largest eigenvalue and corresponding eigenvector
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	public static Vector power_method(Matrix a, BigDecimal tol, Vector u) {
		BigDecimal firstPrevious = u.get(0);

		u = multiply(multiply(a, u), BigDecimal.ONE.divide(u.get(0), 2, RoundingMode.HALF_UP));
		u = power_method(a, tol, u, firstPrevious);
		return u;
	}

	/**
	 * Recursively calculates new eigenvector with power method
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @param prev The previous eigenvalue
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	private static Vector power_method(Matrix a, BigDecimal tol, Vector u, BigDecimal prev) {
		// Sets u to be 1/alpha * Au
		if ((u.get(0).abs().subtract(prev.abs())).abs().compareTo(tol) <= 0) {
			return u;
		}
		prev = u.get(0);
		u = multiply(multiply(a, u), BigDecimal.ONE.divide(u.get(0), 2, RoundingMode.HALF_UP));
		return power_method(a, tol, u, prev);
	}
	

}
