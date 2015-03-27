import java.math.BigDecimal;

/**
 * Hilbert Matrix Procedures using the MatrixCalculator classes
 * 
 * @author Ashika Ganesh and Siddarth Senthilkumar
 * @version 1.0
 */
public class Hilbert {
	private Matrix h;
	private Vector b;

	/**
	 * Constructs an nxn Hilbert Matrix
	 * and a vector b based on the PDF specifications
	 * @param n The number of rows/columns
	 */
	public Hilbert(int n) {
		h = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				h.set(i, j, new BigDecimal("" + (1.0 / (i + j + 1))));
			}
		}

		b = new Vector(n);
		for (int i = 0; i < n; i++) {
			b.set(i, new BigDecimal("" + Math.pow(.1, n / 3)));
		}
	}

	/**
	 * Solves the Hilbert Matrix for the special vector
	 * b associated with it by this class.
	 */
	public void solveUsingLU() {
		Matrix[] lu = MatrixCalculator.lu_fact(h);
		Vector x = MatrixCalculator.solve_lu_b(lu[0], lu[1], b);
		System.out.println("-----------------------");
		System.out.println("HILBERT SOLVED USING LU");
		System.out.println("-----------------------");
		System.out.println("n = " + h.rows());
		System.out.println("Vector x: " + x);
		System.out.println("||LU - H||: " + (MatrixCalculator.subtract(MatrixCalculator.multiply(lu[0], lu[1]), h)).norm());
		System.out.println("||Hx - b||: " + MatrixCalculator.subtract(MatrixCalculator.multiply(h, x), b).norm());
	}

}