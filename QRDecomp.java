import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Representation of a QR Factorization
 *
 * @author Ashika Ganesh and Siddarth Senthilkumar
 * @version 2.3
 */
public class QRDecomp {

	public static Matrix[] qr_fact_househ(Matrix matrix) {
		// Make identity matrix
		Matrix Q = identity(matrix.rows());
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
					temp.set(i, i, BigDecimal.ONE);
				}
				// Insert Hn into relevant areas
				temp.setMatrix(diff, matrix.rows() - 1, diff, matrix.rows() - 1, Hn);
				Hn = temp;
			}
			// Q = H1 H2 H3 H4 .... Hm-1
			Q = MatrixCalculator.multiply(Q, MatrixCalculator.transpose(Hn));
			R = MatrixCalculator.multiply(Hn, R);
		}
		// Make R Upper triangular to account for errors
		for (int row = 0; row < R.rows(); row++) {
			for (int column = 0; column < R.columns(); column++) {
				if (row > column) {
					R.set(row, column, BigDecimal.ZERO);
				}
			}
		}

		// We can factor out -1 from both Q and R
		for (int i = 0; i < Q.rows(); i++) {
			for (int j = 0; j < Q.columns(); j++) {
				Q.set(i, j, Q.get(i, j).multiply(new BigDecimal("-1")));
			}
		}

		for (int i = 0; i < R.rows(); i++) {
			for (int j = 0; j < R.columns(); j++) {
				R.set(i, j, R.get(i, j).multiply(new BigDecimal("-1")));
			}
		}

		Matrix[] A = { Q, R };
		return A;
	}

	private static Matrix calculateH(Matrix matrix, int rowStart) {
		// The column of the matrix including the diagonal and below
		// We want zeros below the diagonal
		Vector a = matrix.getSubVector(rowStart, matrix.rows() - 1, rowStart);
		// Get the magnitude of the subvector
		BigDecimal magnitudeA = a.normF();

		// Create vector e1
		Vector e = new Vector(matrix.rows() - rowStart);
		e.set(0, BigDecimal.ONE);

		// V = a + e1 * ||a1|| where ||a1|| is magnitudeA
		Vector v = MatrixCalculator.add(a, MatrixCalculator.multiply(e, magnitudeA));

		// U = V normalized
		Vector u = MatrixCalculator.multiply(v, BigDecimal.ONE.divide(v.normF(), 10, RoundingMode.HALF_UP));

		// Create Identity matrix
		Matrix I = identity(matrix.rows() - rowStart);

		// H = I - 2uu^t
		Matrix H = MatrixCalculator.subtract(I, MatrixCalculator.multiply(MatrixCalculator.multiply(u, u.transpose()), new BigDecimal("2")));
		return H;
	}

	private static Matrix identity(int n) {
		Matrix identity = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			identity.set(i, i, BigDecimal.ONE);
		}
		return identity;
	}

	public static Matrix[] qr_fact_givens(Matrix matrix) {
		Matrix[] QR = calculateGivens(matrix.getArray());
		return QR;
	}

	public static Matrix[] calculateGivens(BigDecimal[][] A) {
		Matrix R = new Matrix(A);
		BigDecimal[][] Gn = new BigDecimal[A.length][A.length];
		Matrix Q = new Matrix(A.length, A.length);
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (i == j) {
					Gn[i][j] = BigDecimal.ONE;
					Q.set(i, j, BigDecimal.ONE);
				} else {
					Gn[i][j] = BigDecimal.ZERO;
					Q.set(i, j, BigDecimal.ZERO);
				}
			}
		}

		for (int i = 0; i < A[0].length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				BigDecimal x = R.get(i, i);
				BigDecimal y = R.get(j, i);
				BigDecimal cos = x.divide(MatrixCalculator.sqrt(x.pow(2).add(y.pow(2))), 10, RoundingMode.HALF_UP);
				y = y.multiply(new BigDecimal("" + -1));
				BigDecimal sin = y.divide(MatrixCalculator.sqrt(x.pow(2).add(y.pow(2))), 10, RoundingMode.HALF_UP);
				Gn[i][i] = cos;
				Gn[j][i] = sin;
				Gn[i][j] = sin.multiply(new BigDecimal("" + -1));
				Gn[j][j] = cos;
				Matrix GnMatrix = new Matrix(Gn);
				R = MatrixCalculator.multiply(GnMatrix, R);
				Q = MatrixCalculator.multiply(GnMatrix, Q);
				for (int a = 0; a < A.length; a++) {
					for (int b = 0; b < A.length; b++) {
						if (a == b) {
							Gn[a][b] = BigDecimal.ONE;
						} else {
							Gn[a][b] = BigDecimal.ZERO;
						}
					}
				}
			}
		}
		// Make R Upper triangular to account for errors
		for (int row = 0; row < R.rows(); row++) {
			for (int column = 0; column < R.columns(); column++) {
				if (row > column) {
					R.set(row, column, BigDecimal.ZERO);
				}
			}
		}
		Q = MatrixCalculator.transpose(Q);
		Matrix[] QR = { Q, R };
		return QR;
	}
}
