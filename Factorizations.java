/**
 * Representation of LU and QR Factorizations of matrices
 *
 * @author Ashika Ganesh and Siddarth Senthilkumar
 * @version 2.3
 */
public class Factorizations {

	/**
	 * Computes the LU factorization of a matrix
	 * Algorithm learned from http://rosettacode.org/wiki/LU_decomposition
	 * @param matrix The matrix to be factored
	 * @return The matrices
	 */
	public static Matrix[] lu_fact(Matrix a) {
		Matrix l = new Matrix(a.rows(), a.columns());
		Matrix u = new Matrix(a.rows(), a.columns());

		// Put 1s into L's diagonal so that there is a unique solution

		for (int i = 0; i < a.rows(); i++) {
			for (int j = 0; j < a.columns(); j++) {
				if (i == j) {
					l.set(i, j, 1);
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
			double value = a.get(i, 0) / u.get(0, 0);
			l.set(i, 0, value);
		}

		// We now have the first row for U and the first column for L
		// Apply formulas u(i, j) = a(i, j) - sum of k = 0 to i - 1 of u(k, j)*l(i, k)
		// and l(i, j) = 1 / u(j, j) * (a(i, j) - sum of k = 0 to j - 1 of u(k, j)*l(i, k))
		for (int i = 1; i < a.rows(); i++) {
			for (int j = 1; j < a.columns(); j++) {
				if (i <= j) {
					// Only U should be affected
					double sum = 0;
					for (int k = 0; k <= i - 1; k++) {
						sum += u.get(k, j) * l.get(i, k);
					}
					u.set(i, j, a.get(i, j) - sum);
				} else {
					// Lower Triangle, only L affected
					double sum = 0;
					for (int k = 0; k <= j - 1; k++) {
						sum += u.get(k, j) * l.get(i, k);
					}
					l.set(i, j, (a.get(i, j) - sum) / u.get(j, j));
				}
			}
		}
		Matrix[] matrices = {l, u};

		// System.out.println("||LU - A||: " + subtract(multiply(l, u), a).norm());
		return matrices;
	}

	/**
	 * Returns a Matrix array containing the QR factorization
	 * done using HouseHolder reflections.
	 * Assumes the matrix is factorable.
	 * @param matrix The matrix to factor
	 * @return matrix array, where Q = array[0] and R = array[1]
	 */
	public static Matrix[] qr_fact_househ(Matrix matrix) {
		// Make identity matrix
		Matrix Q = Matrix.identity(matrix.rows());
		Matrix R = matrix;
		for (int n = 0; n < matrix.columns() - 1; n++) {
			// Calculates H for this column
			Matrix Hn = calculateH(R, n);

			// If H's dimensions are not that of the original matrix,
			// insert it into the identity matrix so that it is.
			int diff = matrix.rows() - Hn.rows();
			if (diff != 0) {
				// Create necessary portion of identity matrix
				Matrix temp = new Matrix(matrix.rows(), matrix.rows());
				for (int i = 0; i < diff; i++) {
					temp.set(i, i, 1);
				}
				// Insert Hn into relevant areas
				temp.setMatrix(diff, matrix.rows() - 1, diff, matrix.rows() - 1, Hn);
				Hn = temp;
			}
			// Q = H1 H2 H3 H4 .... Hm-1
			Q = MatrixCalculator.multiply(Q, Hn.transpose());
			R = MatrixCalculator.multiply(Hn, R);
		}
		// Make R Upper triangular to account for errors
		for (int row = 0; row < R.rows(); row++) {
			for (int column = 0; column < R.columns(); column++) {
				if (row > column) {
					R.set(row, column, 0);
				}
			}
		}

		// We can factor out -1 from both Q and R
		for (int i = 0; i < Q.rows(); i++) {
			for (int j = 0; j < Q.columns(); j++) {
				Q.set(i, j, Q.get(i, j) * -1);
			}
		}

		for (int i = 0; i < R.rows(); i++) {
			for (int j = 0; j < R.columns(); j++) {
				R.set(i, j, R.get(i, j) * -1);
			}
		}

		Matrix[] A = { Q, R };
		return A;
	}

	/**
	 * Calculates H-hat, a submatrix used in householder reflections.
	 * @param matrix The matrix that is being factored
	 * @param rowStart The row to begin fixing H at.
	 * @return The matrix containing H-hat
	 */
	private static Matrix calculateH(Matrix matrix, int rowStart) {
		// The column of the matrix including the diagonal and below
		// We want zeros below the diagonal
		Vector a = matrix.getSubVector(rowStart, matrix.rows() - 1, rowStart);
		// Get the magnitude of the subvector
		double magnitudeA = a.normF();

		// Create vector e1
		Vector e = new Vector(matrix.rows() - rowStart);
		e.set(0, 1);

		// V = a + e1 * ||a1|| where ||a1|| is magnitudeA
		Vector v = MatrixCalculator.add(a, MatrixCalculator.multiply(e, magnitudeA));

		// U = V normalized
		Vector u = MatrixCalculator.multiply(v, 1. / v.normF());

		// Create Identity matrix
		Matrix I = Matrix.identity(matrix.rows() - rowStart);

		// H = I - 2uu^t
		Matrix H = MatrixCalculator.subtract(I, MatrixCalculator.multiply(MatrixCalculator.multiply(u, u.transpose()), 2));
		return H;
	}

	/**
	 * Returns a Matrix array containing the QR factorization
	 * done using Givens Rotations.
	 * @param matrix The original matrix
	 * @return A matrix array, where Q = array[0] and R = array[1]
	 */
	public static Matrix[] qr_fact_givens(Matrix matrix) {

		double[][] A = matrix.getArray();
		Matrix R = new Matrix(A);
		double[][] Gn = new double[A.length][A.length];
		Matrix Q = new Matrix(A.length, A.length);
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (i == j) {
					Gn[i][j] = 1;
					Q.set(i, j, 1);
				} else {
					Gn[i][j] = 0;
					Q.set(i, j, 0);
				}
			}
		}

		for (int i = 0; i < A[0].length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				double x = R.get(i, i);
				double y = R.get(j, i);
				double cos = x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				y *= -1;
				double sin = y / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				Gn[i][i] = cos;
				Gn[j][i] = sin;
				Gn[i][j] = sin * -1;
				Gn[j][j] = cos;
				Matrix GnMatrix = new Matrix(Gn);
				R = MatrixCalculator.multiply(GnMatrix, R);
				Q = MatrixCalculator.multiply(GnMatrix, Q);
				for (int a = 0; a < A.length; a++) {
					for (int b = 0; b < A.length; b++) {
						if (a == b) {
							Gn[a][b] = 1;
						} else {
							Gn[a][b] = 0;
						}
					}
				}
			}
		}
		// Make R Upper triangular to account for errors
		for (int row = 0; row < R.rows(); row++) {
			for (int column = 0; column < R.columns(); column++) {
				if (row > column) {
					R.set(row, column, 0);
				}
			}
		}
		Q = Q.transpose();
		Matrix[] QR = { Q, R };
		return QR;
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
			double sumOfProducts = 0;
			for (int j = 0; j < i; j++) {
				sumOfProducts += l.get(i, j) * y.get(j);
			}
			// double value = (b.get(i) - sumOfProducts).divide(l.get(i, i));
			double value = b.get(i) - sumOfProducts;
			y.set(i, value);
		}

		// Ux = Y
		// Solve for x, U is upper triangular
		for (int i = y.numElements() - 1; i >= 0; i--) {
			double sumOfProducts = 0;
			for (int j = u.rows() - 1; j > i; j--) {
				sumOfProducts += u.get(i, j) * x.get(j);
			}
			double value = (y.get(i) - sumOfProducts) / u.get(i, i);
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
		Vector d = MatrixCalculator.multiply(q.transpose(), b);

		for (int i = d.numElements() - 1; i >= 0; i--) {
			double sumOfProducts = 0;
			for (int j = r.rows() - 1; j > i; j--) {
				sumOfProducts += r.get(i, j) * x.get(j);
			}
			double value = (d.get(i) - sumOfProducts) / r.get(i, i);
			x.set(i, value);
		}
		return x;
	}
}
