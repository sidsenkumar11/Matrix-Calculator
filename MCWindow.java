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
		MCWindow x = new MCWindow();
		LinkedList<String> fileLocations = new LinkedList<String>();
		fileLocations.add("a.dat");
		// fileLocations.add("b.dat");
		// readMatrices passes
		x.readMatrices(fileLocations);

		// Matrix.toString passes
		for (Matrix a : x.matrices) {
			System.out.println(a);
			System.out.println();
		}

		// Matrix.row(int row) passes
		System.out.println(java.util.Arrays.toString(x.matrices.get(0).row(3)));

		// Matrix.column(int column) passes
		System.out.println(java.util.Arrays.toString(x.matrices.get(0).column(0)));

		// MATRIX CLASS OK

		// Vectors
		double[] vec = {.2, .5, 1.6, 19};
		Vector vector = new Vector(vec);
		System.out.println(vector);
	}

}