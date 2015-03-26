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
		// Matrix - lu_fact

		MCWindow x = new MCWindow();
		LinkedList<String> fileLocations = new LinkedList<String>();
		fileLocations.add("a.dat");
		// fileLocations.add("b.dat");
		// readMatrices passes
		x.readMatrices(fileLocations);

		//System.out.println(MatrixCalculator.lu_fact(x.matrices.get(0))[1]);
		double[][] matrix = {{1, -2, -2, -3}, {3, -9, 0, -9}, {-1, 2, 4, 7}, {-3, -6, 26, 2}};
		Matrix a = new Matrix(matrix);
		System.out.println(MatrixCalculator.lu_fact(a)[0]);
		System.out.println(MatrixCalculator.lu_fact(a)[1]);

	}

}