/**
 * Representation of QR Factorizations of a matrix
 * using Givens Rotations
 *
 * @author Ashika Ganesh
 * @version 1.0
 */
public class qr_fact_givens {

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
					if (Math.abs(R.get(row, column)) <= Math.pow(10, -15)) {
						R.set(row, column, 0);
					}
				}
			}
		}
		Q = Q.transpose();
		Matrix[] QR = { Q, R };
		return QR;
	}
}