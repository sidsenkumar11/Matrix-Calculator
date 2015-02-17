/**
 * Representation of a matrix
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Matrix {
	
	private double[][] matrix;

	/**
	 * Constructs a matrix with the specified
	 * number of rows and columns.
	 * @param rows The number of rows
	 * @param columns The number of columns
	 */
	public Matrix(int rows, int columns) {
		matrix = new double[rows][columns];
	}

	/**
	 * Retrieves an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @return The element in the specified row and column
	 */
	public double get(int row, int column) {
		return matrix[row][column];
	}

	/**
	 * Sets an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @param value The value to be set
	 */
	public void set(int row, int column, double value) {
		matrix[row][column] = value;
	}
}