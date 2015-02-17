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
	 * @throws MatrixIndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public double get(int row, int column) {
		if (row < 0 || column < 0  || row >= rows() || column >= columns()) {
			throw new MatrixIndexOutOfBoundsException();
		}
		return matrix[row][column];
	}

	/**
	 * Sets an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @param value The value to be set
	 * @throws MatrixIndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public void set(int row, int column, double value) {
		if (row < 0 || column < 0  || row >= rows() || column >= columns()) {
			throw new MatrixIndexOutOfBoundsException();
		}
		matrix[row][column] = value;
	}

	/**
	 * The number of rows in the matrix
	 * @return The number of rows
	 */
	public int rows() {
		return matrix.length;
	}

	/**
	 * The number of columns in the matrix
	 * @return The number of columns
	 */
	public int columns() {
		return matrix[0].length;
	}

	/**
	 * Matrices are represented as the following:
	 * [[column 1], [column 2], ... , [column n]]
	 * @return The string representation of this matrix
	 */
	public String toString() {
		//TODO: check this to see that it works properly
		String returnString = "[";
		for (double[] x : matrix) {
			returnString += "[";
			for (double y : x) {
				returnString += y + ", ";
			}
			returnString = returnString.substring(0, returnString.length() - 2);
			returnString += "], ";
		}
		return returnString.substring(0, returnString.length() - 2) + "]";
	}
}