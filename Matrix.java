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
	 * Constructs a matrix with the specified
	 * vector.
	 * @param vector The vector to be represented as a matrix
	 */
	public Matrix(Vector vector) {
		this.matrix = new BigDecimal[vector.rows()][0];
		for (int i = 0; i < vector.rows(); i++) {
			matrix[i][0] = vector.get(i);
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
	 * @return The norm of the matrix
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

	/**
	 *  Returns the frobenius norm of the matrix
	 * (i.e. square root of sum of squares of elements)
	 * @return The largest value in the matrix
	 */
	public BigDecimal normF() {
		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < rows(); i++) {
			for (int j = 0; j < columns(); j++) {
				sum = sum.add(get(i, j).pow(2));
			}
		}
		return MatrixCalculator.sqrt(sum);
	}

	public Matrix getMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {
		Matrix subMatrix = new Matrix(rowEnd - rowStart, colEnd - colStart);
		int subRow = 0;
		int subColumn = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			subColumn = 0;
			for (int j = colStart; j <= colEnd; j++) {
				subMatrix.set(subRow, subColumn, get(i, j));
				subRow++;
			}
			subColumn++;
		}

		return subMatrix;
	}

	public Vector getSubVector(int rowStart, int rowEnd, int column) {
		Vector subVector = new Vector(rowEnd - rowStart);
		int subRow = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			subVector.set(subRow, get(i, column));
			subRow++;
		}

		return subVector;
	}

	public BigDecimal[][] getArray() {
		return matrix;
	}

	private static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    String string = bigDecimal.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    // If no ".", no digits to right of decimal
	    if (index < 0) {
	    	return 0;
	    }

	    // If only 6 digits or less after decimal place,
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
    	index += 1; // Start after the decimal point
	    int numRelevant = 0;
		while (index < string.length() && string.charAt(index) == '0') {
			index++;
			numRelevant++;
		}

		// If index >= string.length(), that means there were only zeros after the decimal point
		// Shouldn't be possible, but if it is, then no significant figures after decimal point.
		if (index >= string.length()) {
			return 0;
		}

		// Otherwise, we hit a nonzero number.
		// Index is pointing to that number
		numRelevant++;

		// Can we hit 5 more numbers?
		// For each one we can hit, increment numRelevant.
		for (int i = 1; i < 6; i++) {
			if (index + i < string.length()) {
				numRelevant++;
			}
		}
		return numRelevant;
	}

	private static int getNumberOfWholeNumbers(BigDecimal bigDecimal) {
		String string = bigDecimal.stripTrailingZeros().toPlainString();
	    int count = 0;
	    for (int i = 0; i < string.length(); i++) {
	    	char x = string.charAt(i);
	    	if (x == '.') {
	    		return count;
	    	} else {
	    		count++;
	    	}
	    }
	    return count;
	}

	private static String getRelevantPortion(BigDecimal y, int numSpaces) {
		String string = y.stripTrailingZeros().toPlainString();
		String relevantPortion = "";

		int index = 0;

		while (index < numSpaces) {
			if (index >= string.length()) {
				index = numSpaces;
			} else {
				relevantPortion += string.substring(index, index + 1);
				index++;
			}
		}

		// Rounding the last digit
		if (index < string.length()) {
			int lastDigit = Integer.parseInt(relevantPortion.substring(relevantPortion.length() - 1));
			int digitAfterLast = Integer.parseInt(string.substring(index, index + 1));
			if (digitAfterLast >= 5) {
				lastDigit++;
			}
			relevantPortion = relevantPortion.substring(0, relevantPortion.length() - 1) + "" + lastDigit;
		}
		return relevantPortion;
	}

	@Override
	public String toString() {
		int largestRelevantRightDecimal = getNumberOfDecimalPlaces(matrix[0][0]);
		int largestNumWholeNumbers = getNumberOfWholeNumbers(matrix[0][0]);
		for (BigDecimal[] x : matrix) {
			for (BigDecimal y : x) {
				if (largestNumWholeNumbers < getNumberOfWholeNumbers(y)) {
					largestNumWholeNumbers = getNumberOfWholeNumbers(y);
				}
				if (largestRelevantRightDecimal < getNumberOfDecimalPlaces(y)) {
					largestRelevantRightDecimal = getNumberOfDecimalPlaces(y);
				}
			}
		}

		int totalSpace = largestNumWholeNumbers + 1 + largestRelevantRightDecimal;
		String formatString = "%" + totalSpace + "s";
		
		String returnString = "";
		for (BigDecimal[] x : matrix) {
			returnString += "|";
			for (BigDecimal y : x) {
				String relevantPortion = getRelevantPortion(y, totalSpace);
				returnString += String.format(formatString, relevantPortion) + " |";
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