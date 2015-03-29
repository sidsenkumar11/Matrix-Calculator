import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Representation of a QR Factorization
 *
 * @author Ashika Ganesh 
 * @version 2.3
 */
public class QRDecomp {

	public static Matrix[] qr_fact_househ(Matrix matrix) {
		int dim = matrix.rows();
		Matrix Q = identity(dim);
		Matrix R = matrix;
		for (int n = 0; n < 3; n++) {
			Matrix Hn = calculateH(R, n);
			int diff = dim - Hn.rows();
			if (diff != 0) {
				Matrix temp = new Matrix(dim, dim);
				for (int i = 0; i < diff; i++) {
					temp.set(i, i, BigDecimal.ONE);
				}
				Hn = temp.getMatrix(diff, dim - 1, diff, dim - 1);
			}
			Q = MatrixCalculator.multiply(Q, MatrixCalculator.transpose(Hn));
			R = MatrixCalculator.multiply(Hn, R);
		}

		Matrix[] A = { Q, R };
		return A;
	}

	private static Matrix calculateH(Matrix matrix, int i) {
		int n = matrix.rows();
		Matrix x = matrix.getMatrix(i, n - 1, i, i);
		BigDecimal xNorm = x.normF();
		Matrix e = new Matrix(n - i, 1);
		e.set(0, 0, BigDecimal.ONE);
		Matrix v = MatrixCalculator.add(x, MatrixCalculator.multiply(e, xNorm));
		Matrix u = MatrixCalculator.multiply(v, BigDecimal.ONE.divide(v.normF(), 10, RoundingMode.HALF_UP));
		Matrix I = identity(n - i);
		Matrix H = MatrixCalculator.subtract(I, MatrixCalculator.multiply(MatrixCalculator.multiply(u, MatrixCalculator.transpose(u)), new BigDecimal("2")));
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