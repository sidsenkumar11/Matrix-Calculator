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
	 * Default value for matrix values is zero.
	 * @param rows The number of rows
	 * @param columns The number of columns
	 */
	public Matrix(int rows, int columns) {
		this.matrix = new double[rows][columns];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	/**
	 * Constructs a matrix with the specified vector.
	 * @param vector The vector to be represented as a matrix
	 */
	public Matrix(Vector vector) {
		this.matrix = new double[vector.rows()][1];
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
		return matrix[row];
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
			desiredColumn[row] = matrix[row][column];
		}
		return desiredColumn;
	}

	/**
	 * Returns the transpose of this matrix.
	 * @return The transpose of the original matrix
	 */
	public Matrix transpose() {
		Matrix transpose = new Matrix(columns(), rows());
		int newRow = 0;
		int newColumn = 0;
		for (int row = 0; row < rows(); row++) {
			newRow = 0;
			for (int column = 0; column < columns(); column++) {
				transpose.set(newRow, newColumn, get(row, column));
				newRow++;
			}
			newColumn++;
		}
		return transpose;
	}

	/**
	 * Calculates the determinant of the matrix.
	 * TODO: CHECK IF THIS IS THE CORRECT FORMULA.
	 * @return The determinant
	 */
	public double determinant() {
		double det = 0;
		if (rows() == 2 && columns() == 2) {
			double crossProduct1 = get(0, 0) * get(1, 1);
			double crossProduct2 = get(1, 0) * get(0, 1);
			det = crossProduct1 * crossProduct2;
		}
		return det;
	}

	/**
	 * Returns the norm of the matrix as defined in the PDF
	 * This norm is the largest of the elements in the matrix.
	 * @return The norm of the matrix
	 */
	public double norm() {
		double largest = get(0, 0);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] - largest > 0) {
					largest = matrix[i][j];
				}
			}
		}
		return largest;
	}

	/**
	 * Returns the frobenius norm of the matrix
	 * This norm is the square root of sum of squares of elements.
	 * @return The largest value in the matrix
	 */
	public double normF() {
		double sum = 0;
		for (int i = 0; i < rows(); i++) {
			for (int j = 0; j < columns(); j++) {
				sum += Math.pow(get(i, j), 2);
			}
		}
		return Math.sqrt(sum);
	}

	/**
	 * Returns a sub-matrix from this matrix
	 * @param rowStart The row to start at
	 * @param rowEnd The row to end at (inclusive)
	 * @param colStart The column to start at
	 * @param colEnd The column to end at (inclusive)
	 * @return The sub-matrix
	 */
	public Matrix getMatrix(int rowStart, int rowEnd, int colStart, int colEnd) {
		Matrix subMatrix = new Matrix(rowEnd - rowStart + 1, colEnd - colStart + 1);
		int subRow = 0;
		int subColumn = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			subColumn = 0;
			for (int j = colStart; j <= colEnd; j++) {
				subMatrix.set(subRow, subColumn++, get(i, j));
			}
			subRow++;
		}
		return subMatrix;
	}

	/**
	 * Sets data from a matrix to a sub-matrix in this matrix
	 * @param rowStart The row to start at
	 * @param rowEnd The row to end at (inclusive)
	 * @param colStart The column to start at
	 * @param colEnd The column to end at (inclusive)
	 */
	public void setMatrix(int rowStart, int rowEnd, int colStart, int colEnd, Matrix subMatrix) {
		int subRow = 0;
		int subColumn = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			subColumn = 0;
			for (int j = colStart; j <= colEnd; j++) {
				set(i, j, subMatrix.get(subRow, subColumn++));
			}
			subRow++;
		}
	}

	/**
	 * Returns a sub-vector from this matrix
	 * @param rowStart The row to start at
	 * @param rowEnd The row to end at (inclusive)
	 * @param column The column this sub-vector is in
	 * @return The sub-vector
	 */
	public Vector getSubVector(int rowStart, int rowEnd, int column) {
		Vector subVector = new Vector(rowEnd + 1 - rowStart);
		int subRow = 0;
		for (int i = rowStart; i <= rowEnd; i++) {
			subVector.set(subRow++, get(i, column));
		}
		return subVector;
	}

	/**
	 * Returns the backing array for this matrix
	 * @return matrix The backing array for this matrix
	 */
	public double[][] getArray() {
		return matrix;
	}

	/**
	 * Returns an identity matrix with n rows and n columns
	 * @param n The number of rows/columns
	 * @return The identity matrix
	 */
	public static Matrix identity(int n) {
		Matrix identity = new Matrix(n, n);
		for (int i = 0; i < n; i++) {
			identity.set(i, i, 1);
		}
		return identity;
	}

	/**
	 * THE FOLLOWING CODE IS USED FOR REPRESENTING THE MATRIX IN STANDARD OUTPUT
	 * RULES HAVE BEEN IMPLEMENTED AS FOLLOWS:
	 *
	 * 1) THE MATRIX BEGINS AND ENDS WITH A LINE OF DASHES (---------------------)
	 * 2) EACH ROW HAS THE SAME WIDTH AND EACH ELEMENT IN EACH ROW HAS THE SAME WIDTH
	 * 3) THE LARGEST WIDTH OF ANY INDIVIDUAL ELEMENT IS USED AS THE WIDTH FOR EACH ELEMENT
	 * 4) A) IF THE NUMBER OF DECIMAL PLACES EXCEEDS 6, THE WIDTH IS FIXED TO A CERTAIN NUMBER "NUMSPACE"
	 *    B) NUMSPACES IS DEFINED AS:
	 *		 1) THE NUMBER OF DIGITS IN THE WHOLE NUMBER PORTION OF THE BIGDECIMAL +
	 *		 2) THE DECIMAL POINT (1 SPACE) +
	 *		 3) THE NUMBER OF DIGITS AFTER THE DECIMAL POINT. EXCLUDING INTIAL ZEROS, IF THIS NUMBER EXCEEDS 6,
	 *			WE SET THE RIGHT SIDE PORTION TO BE #LEADING ZEROS + UP TO 6 DIGITS
	 *			IN THIS WAY, WE ALWAYS END UP WITH AT MOST 6 SIGNIFICANT FIGURES AFTER THE DECIMAL POINT
	 */
	private static int getNumberOfDecimalPlaces(double number) {
	    String string = "" + number;
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

	private static int getNumberOfWholeNumbers(double number) {
		String string = "" + number;
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

	private static String getRelevantPortion(double y, int numSpaces) {
		String string = "" + y;
		String relevantPortion = "";

		int index = 0;
		String remainder = "";
		if (string.indexOf('E') != -1) {
			// String is in scientific notation
			remainder = string.substring(string.indexOf('E'));
			string = string.substring(0, string.indexOf(remainder));
			numSpaces -= remainder.length();
		}

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
		return relevantPortion + remainder;
	}

	@Override
	public String toString() {
		int largestRelevantRightDecimal = getNumberOfDecimalPlaces(matrix[0][0]);
		int largestNumWholeNumbers = getNumberOfWholeNumbers(matrix[0][0]);
		for (double[] x : matrix) {
			for (double y : x) {
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
		for (double[] x : matrix) {
			returnString += "|";
			for (double y : x) {
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