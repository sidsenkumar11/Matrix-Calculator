import java.util.LinkedList;

/**
 * Representation of a matrix
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Matrix {

	private double[][] matrix;

	/**
	 * Constructs a matrix with the specified data
	 * @param rows A 2D array made from a linked list and string array
	 */
	public Matrix(LinkedList<String[]> rows) {
		this.matrix = new double[rows.size()][rows.get(0).length];
		for (int row = 0; row < rows.size(); row++) {
			for (int column = 0; column < rows.get(row).length; column++) {
				set(row, column, Double.parseDouble(rows.get(row)[column]));
			}
		}
	}

	/**
	 * Constructs a matrix with the specified data
	 * @param matrix The matrix in 2D array form
	 */
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * Constructs a matrix with the specified
	 * number of rows and columns.
	 * @param rows The number of rows
	 * @param columns The number of columns
	 */
	public Matrix(int rows, int columns) {
		this.matrix = new double[rows][columns];
	}

	/**
	 * Retrieves an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @return The element in the specified row and column
	 * @throws IndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public double get(int row, int column) {
		if (row < 0 || column < 0  || row >= rows() || column >= columns()) {
			throw new IndexOutOfBoundsException();
		}
		return matrix[row][column];
	}

	/**
	 * Sets an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @param value The value to be set
	 * @throws IndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public void set(int row, int column, double value) {
		if (row < 0 || column < 0  || row >= rows() || column >= columns()) {
			throw new IndexOutOfBoundsException();
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
	 * Returns an array of the elements in
	 * the specified row.
	 * @param row The desired row of elements
	 * @return An array with the data from the row
	 * @throws IndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public double[] row(int row) {
		if (row < 0 || row >= rows()) {
			throw new IndexOutOfBoundsException();
		}
		double[] desiredRow = new double[columns()];
		for (int col = 0; col < columns(); col++) {
			desiredRow[col] = get(row, col);
		}
		return desiredRow;
	}

	/**
	 * Returns an array of the elements in
	 * the specified column.
	 * @param column The desired column of elements
	 * @return An array with the data from the column
	 * @throws IndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public double[] column(int column) {
		if (column < 0  || column >= columns()) {
			throw new IndexOutOfBoundsException();
		}

		double[] desiredColumn = new double[rows()];
		for (int row = 0; row < rows(); row++) {
			desiredColumn[row] = get(row, column);
		}
		return desiredColumn;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (double[] x : matrix) {
			returnString += "|";
			for (double y : x) {
				returnString += String.format("%10s", y) + " |";
			}
			returnString += "\t\n";
		}

		String border = "";
		int rowLength = (returnString.length() - 1) / matrix.length;
		for (int i = 0; i < rowLength; i++) {
			border += "-";
		}
		returnString = border + "\n" + returnString + border;
		return returnString;
	}
}