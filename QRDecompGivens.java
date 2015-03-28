import java.util.ArrayList;

/**
 * QR Decomposition using Givens Rotations
 * @version 
 */
public class QRDecompGivens extends QRDecomp{

	
	/**
	 * Constructor to decompose the Matrix into QR using Givens
	 * rotations
	 * @param A Matrix to decompose
	 */
	public QRDecompGivens(Matrix A) {
		this.R = A;
		double[][] rArr = R.getArray();
		m = A.getRowDimension();
		n = A.getRowDimension();
		qMatrices = new ArrayList<Matrix>();
		//TODO Do the QR stuff here
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				rArr = R.getArray();
				double givVec[] = {rArr[i][i], rArr[j][i]};
				if (!checkZeroes(givVec)) {
					Matrix G = givensMatrix(givVec, i ,j);
					qMatrices.add(G);
					R = G.multiply(R);
				}
			}
		}
		this.Q = qMatrices.get(qMatrices.size() - 1).transpose();
		for (int i = qMatrices.size() - 2; i >= 0; i--) {
			Matrix G = qMatrices.get(i).transpose();
			Q =  G.multiply(Q);
		}
	}

	
	/**
	 * Calculates the givens matrix based off a a vector
	 * @param givVec A vector for the two numbers
	 * @param i Row index
	 * @param j Column Index
	 * @return The Givens matrix
	 */
	private static Matrix givensMatrix(double[] givVec, int i, int j) {
		double x = givVec[0];
		double y = givVec[1];
		double cos = x / Math.sqrt(x * x + y * y);
		double sin = -y / Math.sqrt(x * x + y * y);
		//put into matrix of same size;
		Matrix G = Matrix.identity(m, n);
		G.set(i, i, cos);
		G.set(i, j, -1 * sin);
		G.set(j, i, sin);
		G.set(j, j, cos);
		//G.print(5, 6);
		return G;
	}
	
}