/**
 * Solves for vector x in Ax = b
 * Assuming A has been factored into QR form.
 *
 * @author Siddarth Senthilkumar
 * @version 2.0
 */
public class solve_qr_b {

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