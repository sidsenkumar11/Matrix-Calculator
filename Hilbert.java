import java.math.BigDecimal;

/**
 * Hilbert Matrix Procedures using the MatrixCalculator classes
 * 
 * @author Siddarth Senthilkumar
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
			b.set(i, new BigDecimal("" + Math.pow(.1, n / 3.0)));
		}
	}

	/**
	 * Solves the Hilbert Matrix for the special vector
	 * b associated with it by this class.
	 * Uses LU Decomposition
	 */
	public void solveUsingLU() {
		System.out.println("n = " + h.rows());
		System.out.println("--------------------------");
		System.out.println("Original Hilbert Matrix");
		System.out.println("--------------------------");
		System.out.println(h);
		System.out.println("--------------------------");
		System.out.println("B vector in Ax = b");
		System.out.println("--------------------------");
		System.out.println(b);
		Matrix[] lu = MatrixCalculator.lu_fact(h);
		Vector x = MatrixCalculator.solve_lu_b(lu[0], lu[1], b);
		System.out.println("--------------------------");
		System.out.println("HILBERT SOLVED USING LU");
		System.out.println("--------------------------");
		System.out.println("1) Lower Triangular Matrix");
		System.out.println(lu[0]);
		System.out.println("2) Upper Triangular Matrix");
		System.out.println(lu[1]);
		System.out.println("Vector x: " + x);
		System.out.println("||LU - H||: " + (MatrixCalculator.subtract(MatrixCalculator.multiply(lu[0], lu[1]), h)).norm());
		System.out.println("||Hx - b||: " + MatrixCalculator.subtract(MatrixCalculator.multiply(h, x), b).norm());
	}

	/**
	 * Shows solutions to QR
	 */
	public void solveUsingQR() {
		solveUsingQRHouseHolder();
		solveUsingQRGivens();
	}

	/**
	 * Solves the Hilbert Matrix for the special vector
	 * b associated with it by this class.
	 * Uses QR Decomposition with HouseHolder
	 */
	public void solveUsingQRHouseHolder() {
		System.out.println("n = " + h.rows());
		System.out.println("--------------------------");
		System.out.println("Original Hilbert Matrix");
		System.out.println("--------------------------");
		System.out.println(h);
		System.out.println("--------------------------");
		System.out.println("B vector in Ax = b");
		System.out.println("--------------------------");
		System.out.println(b);
		Matrix[] qr = MatrixCalculator.qr_fact_househ(h);
		Vector x = MatrixCalculator.solve_qr_b(qr[0], qr[1], b);
		System.out.println("-----------------------------------------------------");
		System.out.println("HILBERT SOLVED USING QR USING HOUSEHOLDER REFLECTIONS");
		System.out.println("-----------------------------------------------------");
		System.out.println("1) Q Matrix");
		System.out.println(qr[0]);
		System.out.println("2) R Matrix");
		System.out.println(qr[1]);
		System.out.println("Vector x: " + x);
		System.out.println("||QR - H||: " + (MatrixCalculator.subtract(MatrixCalculator.multiply(qr[0], qr[1]), h)).norm());
		System.out.println("||Hx - b||: " + MatrixCalculator.subtract(MatrixCalculator.multiply(h, x), b).norm());
	}

	/**
	 * Solves the Hilbert Matrix for the special vector
	 * b associated with it by this class.
	 * Uses QR Decomposition with Givens
	 */
	public void solveUsingQRGivens() {
		System.out.println("n = " + h.rows());
		System.out.println("--------------------------");
		System.out.println("Original Hilbert Matrix");
		System.out.println("--------------------------");
		System.out.println(h);
		System.out.println("--------------------------");
		System.out.println("B vector in Ax = b");
		System.out.println("--------------------------");
		System.out.println(b);
		Matrix[] qr = MatrixCalculator.qr_fact_givens(h);
		Vector x = MatrixCalculator.solve_qr_b(qr[0], qr[1], b);
		System.out.println("------------------------------------");
		System.out.println("HILBERT SOLVED USING QR USING GIVENS");
		System.out.println("------------------------------------");
		System.out.println("1) Q Matrix");
		System.out.println(qr[0]);
		System.out.println("2) R Matrix");
		System.out.println(qr[1]);
		System.out.println("Vector x: " + x);
		System.out.println("||QR - H||: " + (MatrixCalculator.subtract(MatrixCalculator.multiply(qr[0], qr[1]), h)).norm());
		System.out.println("||Hx - b||: " + MatrixCalculator.subtract(MatrixCalculator.multiply(h, x), b).norm());
	}
}