import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

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
		// ... continued - lu_fact, solve_lu_b

		// MCWindow x = new MCWindow();
		// LinkedList<String> fileLocations = new LinkedList<String>();
		// fileLocations.add("a.dat");
		// fileLocations.add("b.dat");
		// x.readMatrices(fileLocations);

		//System.out.println(MatrixCalculator.lu_fact(x.matrices.get(0))[1]);
		// double[][] matrix = {{1, .5, .333333, .25}, {.5, .333333, .25, .2}, {.333333, .25, .2, .166667}, {.25, .2, .166667, .142857}};
		// Matrix a = new Matrix(matrix);
		// System.out.println(MatrixCalculator.lu_fact(a)[0]);
		// System.out.println(MatrixCalculator.lu_fact(a)[1]);

		double[][] matrix = {{2, 4, -4}, {1, -4, 3}, {-6, -9, 10}};
		Matrix a = new Matrix(matrix);
		Matrix[] lu = MatrixCalculator.lu_fact(a);
		double[] b = {12, -21, -24};
		Vector bb = new Vector(b);
		System.out.println((MatrixCalculator.solve_lu_b(lu[0], lu[1], bb)));

	}

}