import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Creates the interface for displaying.
 * @author Siddarth Senthilkumar and Ashika Ganesh
 * @version 1.0
 */
public class MCWindow {

	private LinkedList<Matrix> matrices;

	public MCWindow() {
		this.matrices = new LinkedList<Matrix>();
	}

	/**
	 * Read in all Matrix .dat files into matrices
	 */
	public void readMatrices(LinkedList<String> fileNames) {

		for (String fileName : fileNames) {
			try {

				FileReader reader = new FileReader(fileName);
				Scanner in = new Scanner(reader);
				LinkedList<String[]> rows = new LinkedList<String[]>();

				// Read in one row at a time into a LinkedList of arrays
				while(in.hasNextLine()) {
					String[] row = in.nextLine().split(" ");
					rows.add(row);
				}

				// Create Matrix
				Matrix matrix = new Matrix(rows);
				matrices.add(matrix);

			} catch(IOException exception) {
				System.out.println("Error processing file: " + exception);
			}
		}

	}

	/**
	 * For testing that methods work
	 */
	public static void main(String[] args) {
		// Things I have finished testing below:
		// MCWindow - readMatrices
		// Matrix
		// Vector
		// MatrixCalculator - add, subtract, multiply by scalar, multiply 2 matrices, dot product, transpose
		// ... continued - lu_fact, solve_lu_b, solve_qr_b

		// Tested whether matrices were read properly from a file
		// MCWindow x = new MCWindow();
		// LinkedList<String> fileLocations = new LinkedList<String>();
		// fileLocations.add("a.dat");
		// fileLocations.add("b.dat");
		// x.readMatrices(fileLocations);

		// Tested LU factorization of A
		//System.out.println(MatrixCalculator.lu_fact(x.matrices.get(0))[1]);
		// double[][] matrix = {{1, .5, .333333, .25}, {.5, .333333, .25, .2}, {.333333, .25, .2, .166667}, {.25, .2, .166667, .142857}};
		// Matrix a = new Matrix(matrix);
		// System.out.println(MatrixCalculator.lu_fact(a)[0]);
		// System.out.println(MatrixCalculator.lu_fact(a)[1]);

		// Tested Solving Ax = b for A = LU
		// double[][] matrix = {{2, 4, -4}, {1, -4, 3}, {-6, -9, 10}};
		// Matrix a = new Matrix(matrix);
		// Matrix[] lu = MatrixCalculator.lu_fact(a);
		// double[] b = {12, -21, -24};
		// Vector bb = new Vector(b);
		// System.out.println((MatrixCalculator.solve_lu_b(lu[0], lu[1], bb)));

		// Tested Solving Ax = b for A = QR
		// double[][] qq = {{.6, 0}, {.8, 0}, {0, 1}};
		// Matrix q = new Matrix(qq);
		// double[][] rr = {{5, -10}, {0, 1}};
		// Matrix r = new Matrix(rr);
		// double[] b = {-1, 7, 2};
		// Vector bb = new Vector(b);
		// System.out.println((MatrixCalculator.solve_qr_b(q, r, bb)));

		// Testing Hilbert class
		// Hilbert testOne = new Hilbert(4);
		// testOne.solveUsingLU();
		// testOne.solveUsingQR();

		// Testing power method
		// double[][] matrix = {{2, 7}, {-1, -6}};
		// double[] guessVector = {-7, 1};

		// Matrix eigenMatrix = new Matrix(matrix);
		// Vector vector = new Vector(guessVector);

		// System.out.println(MatrixCalculator.power_method(eigenMatrix, new BigDecimal("" + .00001), vector));
	
		// Testing QR factorization of A
		// double[][] matrixQR = {{1, 0, 1}, {0, 1, 1}, {1, 1, 0}};
		// Matrix matrix = new Matrix(matrixQR);
		// System.out.println(Factorizations.qr_fact_househ(matrix)[0]);
		// System.out.println(Factorizations.qr_fact_househ(matrix)[1]);

		// Testing Leslie class

		// Leslie leslie = new Leslie();
		
		// Testing power_method

		double[][] initMatrix = {{2, 0, 0}, {0, 2, 0}, {0, 0, 1}};
		double[] guessVector = {1, 0, 0};
		Matrix matrix = new Matrix(initMatrix);
		Vector vector = new Vector(guessVector);

		System.out.println(MatrixCalculator.power_method(matrix, new BigDecimal(".01"), vector));
		
	}

}