import Jama.Matrix;
//used jama package
/**
 * Representation of a QR Factorization
 *
 * @author Ashika Ganesh 
 * @version 2.3
 */
public class QRDecomp {

	public static Matrix[] qr_fact_househ(Matrix matrix) {
		int dim = matrix.getRowDimension();
		Matrix Q = identity(dim);
		Matrix R = matrix;
		for (int n = 0; n < 3; n++) {
			Matrix Hn = calculateH(R, n);
			int diff = dim - Hn.getRowDimension();
			if (diff != 0) {
				Matrix temp = new Matrix(dim, dim);
				for (int i = 0; i < diff; i++) {
					temp.set(i, i, 1);
				}
				temp.setMatrix(diff, dim - 1, diff, dim - 1, Hn);
				Hn = temp;
			}
			Q = Q.times(Hn.transpose());
			R = Hn.times(R);
		}
		Matrix[] A = { Q, R };
		return A;
	}

	private static Matrix calculateH(Matrix matrix, int i) {
		int n = matrix.getRowDimension();
		Matrix x = matrix.getMatrix(i, n - 1, i, i);
		double xNorm = x.normF();
		Matrix e = new Matrix(n - i, 1);
		e.set(0, 0, 1);
		Matrix v = x.plus(e.times(xNorm));
		Matrix u = v.times(1 / (v.normF()));
		Matrix I = identity(n - i);
		Matrix H = I.minus((u.times(u.transpose())).times(2));
		return H;
	}

	private static Matrix identity(int n) {
		Matrix identity = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			identity.set(i, i, 1);
		}
		return identity;
	}

	public static Matrix[] qr_fact_givens(Matrix matrix) {
		Matrix[] QR = calculateGivens(matrix.getArray());
		return QR;
	}

	public static Matrix[] calculateGivens(double[][] A) {
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
				double cos = x / (Math.sqrt(x * x + y * y));
				double sin = -(y) / (Math.sqrt(x * x + y * y));
				Gn[i][i] = cos;
				Gn[j][i] = sin;
				Gn[i][j] = -sin;
				Gn[j][j] = cos;
				Matrix GnMatrix = new Matrix(Gn);
				R = GnMatrix.times(R);
				Q = GnMatrix.times(Q);
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
		Q = Q.transpose();
		Matrix[] QR = { Q, R };
		return QR;
	}

	public static void main(String[] args) {
		
	}
}