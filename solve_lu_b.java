/**
 * Solves for vector x in Ax = b
 * Assuming A has been factored into LU form.
 *
 * @author Siddarth Senthilkumar
 * @version 2.0
 */
public class solve_lu_b {

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
}