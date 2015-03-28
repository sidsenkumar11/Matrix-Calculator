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

	/**
	 *  Returns the norm of the matrix as defined in the PDF
	 * @return The largest value in the matrix
	 */
	public BigDecimal norm() {
		BigDecimal largest = get(0, 0);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j].compareTo(largest) > 0) {
					largest = matrix[i][j];
				}
			}
		}
		return largest;
	}

	private int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    String string = bigDecimal.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    // If no ".", no digits to right of decimal
	    if (index < 0) {
	    	return 0;
	    }

	    // If only 6 digits after decimal place,
	    // then that's fine
    	int numAfterDecimal = string.length() - index - 1;
    	if (numAfterDecimal <= 6) {
    		return numAfterDecimal;
    	}

    	// If more than 6 digits after decimal place,
    	// find number of relevant digits
    	// i.e. number of zeros then 6 digits

    	// Start at the decimal point
    	// Increment numRelevant and index until we stop getting zeros
	    int numRelevant = 0;
		while (index < string.length() && string.charAt(index) == '0') {
			index++;
			numRelevant++;
		}
		return numRelevant;
	}

	private int getNumberOfWholeNumbers(BigDecimal bigDecimal) {
		String string = bigDecimal.stripTrailingZeros().toPlainString();
		// Always at least 1 for zero
	    int count = 1;
	    for (char x : string.toCharArray()) {
	    	if (x == '.') {
	    		return count;
	    	} else {
	    		count++;
	    	}
	    }
	    return count;
	}

	@Override
	public String toString() {
		// Find longest length string length of a number in the matrix
		// Find the largest number of numbers to the right of the decimal point in the matrix
		int largestNumDecimalPlaces = getNumberOfDecimalPlaces(matrix[0][0]);
		int largestNumWholeNumbers = getNumberOfWholeNumbers(matrix[0][0]);
		for (BigDecimal[] x : matrix) {
			for (BigDecimal y : x) {
				if (largestNumWholeNumbers < getNumberOfWholeNumbers(y)) {
					largestNumWholeNumbers = getNumberOfWholeNumbers(y);
				}
				if (largestNumDecimalPlaces < getNumberOfDecimalPlaces(y)) {
					largestNumDecimalPlaces = getNumberOfDecimalPlaces(y);
				}
			}
		}

		String formatString = null;
		int totalSpace = largestNumWholeNumbers;
		if (largestNumDecimalPlaces <= 6) {
			formatString = "%" + 10 + "s";
		}

		String returnString = "";
		for (BigDecimal[] x : matrix) {
			returnString += "|";
			for (BigDecimal y : x) {
				if (formatString != null) {
					returnString += String.format(formatString, y) + " |";
				} else {
					String num = y.stripTrailingZeros().toPlainString();
					int indexOfFirstNonZeroAfterDecimal = num.indexOf(".") + 1;
					String relevantPortion;

					if (indexOfFirstNonZeroAfterDecimal != -1) {
						while (num.charAt(indexOfFirstNonZeroAfterDecimal) == '0') {
							indexOfFirstNonZeroAfterDecimal++;
						}
						if (indexOfFirstNonZeroAfterDecimal + 5 >= num.length()) {
							relevantPortion = num;
						} else {
							relevantPortion = num.substring(0, indexOfFirstNonZeroAfterDecimal + 6);
						}
					} else {
						relevantPortion = num;
					}
					returnString += String.format("%10s", relevantPortion);
				}
			}
			returnString += "\n";
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