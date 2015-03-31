/**
 * Representation of LU Factorizations of a matrix
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class lu_fact {
	
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
}