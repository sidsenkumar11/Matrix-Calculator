import java.util.LinkedList;
import java.math.BigDecimal;

/**
 * Representation of a matrix
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Matrix {

	private BigDecimal[][] matrix;

	/**
	 * Constructs a matrix with the specified data
	 * @param rows A 2D array made from a linked list and string array
	 */
	public Matrix(LinkedList<String[]> rows) {
		this.matrix = new BigDecimal[rows.size()][rows.get(0).length];
		for (int row = 0; row < rows.size(); row++) {
			for (int column = 0; column < rows.get(row).length; column++) {
				set(row, column, new BigDecimal(rows.get(row)[column]));
			}
		}
	}

	/**
	 * Constructs a matrix with the specified data
	 * @param matrix The matrix in 2D array form
	 */
	public Matrix(BigDecimal[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * Constructs a matrix with the specified data
	 * @param matrix The matrix in 2D array form
	 */
	public Matrix(double[][] matrix) {
		this.matrix = new BigDecimal[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				this.matrix[i][j] = new BigDecimal("" + matrix[i][j]);
			}
		}
	}

	/**
	 * Constructs a matrix with the specified
	 * number of rows and columns.
	 * @param rows The number of rows
	 * @param columns The number of columns
	 */
	public Matrix(int rows, int columns) {
		this.matrix = new BigDecimal[rows][columns];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = BigDecimal.ZERO;
			}
		}
	}

	/**
	 * Retrieves an element in the matrix
	 * @param row The row of the element
	 * @param column The column of the element
	 * @return The element in the specified row and column
	 * @throws IndexOutOfBoundsException if row and column
	 * 		   are not valid in the matrix
	 */
	public BigDecimal get(int row, int column) {
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
	public void set(int row, int column, BigDecimal value) {
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
	public BigDecimal[] row(int row) {
		if (row < 0 || row >= rows()) {
			throw new IndexOutOfBoundsException();
		}
		BigDecimal[] desiredRow = new BigDecimal[columns()];
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
	public BigDecimal[] column(int column) {
		if (column < 0  || column >= columns()) {
			throw new IndexOutOfBoundsException();
		}

		BigDecimal[] desiredColumn = new BigDecimal[rows()];
		for (int row = 0; row < rows(); row++) {
			desiredColumn[row] = get(row, column);
		}
		return desiredColumn;
	}

	@Override
	public String toString() {
		String returnString = "";
		for (BigDecimal[] x : matrix) {
			returnString += "|";
			for (BigDecimal y : x) {
				returnString += String.format("%15s", y) + " |";
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