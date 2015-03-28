import java.util.ArrayList;
/**
 * QR Decomposition using Householder reflections
 * @version 1.0
 */
public class QRDecompHouseHolder extends QRDecomp{

	/**
	 * Constructor to decompose the Matrix into QR using household
	 * reflections
	 * @param A Matrix to decompose
	 */
	public QRDecompHouseHolder(Matrix A) {
		//this.A = A;
		this.R = A;
		//QR = A.getArrayCopy();
		m = A.getRowDimension();
		n = A.getColumnDimension();
		qMatrices = new ArrayList<Matrix>();
		//TODO Do the QR stuff here
		for (int i = 0; i < n - 1; i++) {
			//look at the column vectors along the diagonal
			double[] colVec = R.getColumnVector(i, i);
			//check if the vector needs a reflection applied to it
			if (!checkZeroes(colVec)) {
				double[] hhVec = houseHolderVector(colVec);
				Matrix hhMat = houseHolderMatrix(hhVec);
				//hhMat.print(5, 5);
				qMatrices.add(hhMat);
				R = hhMat.multiply(R);
			}
		}
		this.Q = qMatrices.get(qMatrices.size() - 1);
		for (int i = qMatrices.size() - 2; i >= 0; i--) {
			Matrix h = qMatrices.get(i);
			Q =  h.multiply(Q);
		}
		Q = Q.uminus();
		R = R.uminus();
	}
	

	//Solve Ax = b
	//QRx = b
	//Qy = b
	//Rx = y
	//Q^tb = y

	
	/**
	 * Private method to get the householder vector
	 * @param vector Vector to reflect
	 * @return the householder reflection vector
	 */
	private static double[] houseHolderVector(double[] x) {
		double[] houseHolder = new double[x.length];
		double[] e = new double[x.length];
		double normX = Matrix.norm(x);
		e[0] = normX;
		//check if the vector needs it?
		// v = x + ||x||e
		houseHolder = Matrix.plus(x, e);
		// u = v / ||v||
		double normV = Matrix.norm(houseHolder);
		houseHolder = Matrix.multiply(houseHolder, 1 / normV);
		return houseHolder;
	}
	
	/**
	 * H = I - 2uu^t
	 * Get the householder matrix
	 * @param hhVec The householder vector 
	 * @return The householder matrix
	 */
	private static Matrix houseHolderMatrix(double[] hhVec) {
		//u * u^t
		Matrix X = Matrix.multiplyVectors(hhVec, hhVec);
		//2 * u * u^t
		X.timesEquals(2);
		//I
		Matrix houseHolder = Matrix.identity(X.getRowDimension(), X.getColumnDimension());
		// I - 2uu^t
		houseHolder.minusEquals(X);
		//put matrix into the same sized matrix as original if it is smaller than original
		int hhM = houseHolder.getRowDimension();
		int hhN = houseHolder.getColumnDimension();
		if (m != hhM || n != hhN) {
			Matrix I = Matrix.identity(m, n);
			I.setMatrix(m - hhM, m - 1, n - hhN, n - 1, houseHolder);
			houseHolder = I;
		}
		
		return houseHolder;
	}
	



}