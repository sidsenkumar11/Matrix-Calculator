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

		double[] vec = {.2, .5, 1.6, 19};
		Vector vector = new Vector(vec);
		System.out.println(vector);

		Matrix one = new Matrix(5, 5);
		Matrix two = new Matrix(5, 5);
		int counter = 0;
		for (int i = 0; i < one.rows(); i++) {
			for (int j = 0; j < one.columns(); j++) {
				one.set(i, j, counter++);
				two.set(i, j, 3);
			}
		}

		System.out.println(MatrixCalculator.subtract(one, two));

		System.out.println(MatrixCalculator.multiply(one, 5));

		LinkedList<String[]> matOne = new LinkedList<String[]>();
		String[] arrOne = {"1", "3", "5"};
		 String[] arrt = {"2", "4", "6"};
		matOne.add(arrOne);
		matOne.add(arrt);

		one = new Matrix(matOne);


		LinkedList<String[]> matTwo = new LinkedList<String[]>();
		// String[] arr = {"2", "1", "3"};
		// String[] arrTwo = {"3", "3", "2"};
		// String[] arrThree = {"4", "1", "2"};
		String[] arr = {"3", "6"};
		String[] arrTwo = {"1", "4"};
		String[] arrThree = {"5", "2"};
		matTwo.add(arr);
		matTwo.add(arrTwo);
		matTwo.add(arrThree);
		two = new Matrix(matTwo);

		System.out.println(MatrixCalculator.multiply(one, two));

		System.out.println(MatrixCalculator.dotProduct(vector, vector));
		System.out.println((one));

		System.out.println(MatrixCalculator.transpose(one));

	}

}