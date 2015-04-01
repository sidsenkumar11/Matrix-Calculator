/**
 * Representation of QR Factorizations of a matrix
 * using HouseHolder Reflections
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class qr_fact_househ {

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
					if (Math.abs(R.get(row, column)) <= Math.pow(10, -15)) {
						R.set(row, column, 0);
					}
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
}