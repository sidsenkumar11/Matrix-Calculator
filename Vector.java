/**
 * Representation of a vector
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Vector {
	
	private double[] vector;

	/**
	 * Constructs a vector with the specified
	 * number of rows.
	 * Default value for vector values is zero.
	 * @param rows The number of rows
	 */
	public Vector(int rows) {
		this.vector = new double[rows];
		for (int i = 0; i < rows; i++) {
			vector[i] = 0;
		}
	}

	/**
	 * Constructs a vector with the double array's data
	 * @param vector The vector
	 */
	public Vector(double[] vector) {
		this.vector = vector;
	}

	/**
	 * Retrieves an element in the vector
	 * @param row The row of the element
	 * @return The element in the specified row
	 * @throws IndexOutOfBoundsException if row 
	 * 		   is not valid in the vector
	 */
	public double get(int row) {
		if (row < 0 || row >= rows()) {
			throw new IndexOutOfBoundsException();
		}
		return vector[row];
	}

	/**
	 * Sets an element in the vector
	 * @param row The row of the element
	 * @param value The value to be set
	 * @throws IndexOutOfBoundsException if row
	 * 		   is not valid in the vector
	 */
	public void set(int row, double value) {
		if (row < 0 || row >= rows()) {
			throw new IndexOutOfBoundsException();
		}
		vector[row] = value;
	}

	/**
	 * The number of rows in the vector
	 * @return The number of rows
	 */
	public int rows() {
		return vector.length;
	}

	/**
	 * The number of elements in the vector
	 * @return The number of elements
	 */
	public int numElements() {
		return rows();
	}

	/**
	 * Returns the transpose of the given vector.
	 * @return The transpose of the original vector
	 */
	public Matrix transpose() {
		Matrix transpose = new Matrix(1, numElements());
		for (int column = 0; column < rows(); column++) {
			transpose.set(0, column, get(column));
		}
		return transpose;
	}

	/**
	 * Returns the norm of the vector as defined in the PDF
	 * This norm is the largest value in the vector.
	 * @return The largest value in the vector
	 */
	public double norm() {
		double largest = get(0);
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] - largest > 0) {
				largest = vector[i];
			}
		}
		return largest;
	}

	/**
	 * Returns the frobenius norm of the vector
	 * This norm is the square root of sum of squares of the elements in the vector.
	 * @return The norm of the matrix
	 */
	public double normF() {
		double sum = 0;
		for (int i = 0; i < rows(); i++) {
			sum += Math.pow(get(i), 2);
		}
		return Math.sqrt(sum);
	}

	/**
	 * THE FOLLOWING CODE IS USED FOR REPRESENTING THE VECTOR IN STANDARD OUTPUT
	 * RULES HAVE BEEN IMPLEMENTED AS FOLLOWS:
	 *
	 * 1) THE VECTOR WILL BE REPRESENTED AS ITS TRANSPOSE
	 * 2) EACH ELEMENT IN THE ROW HAS THE SAME WIDTH
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

	public String toString() {

		int largestRelevantRightDecimal = getNumberOfDecimalPlaces(vector[0]);
		int largestNumWholeNumbers = getNumberOfWholeNumbers(vector[0]);
		for (double y : vector) {
			if (largestNumWholeNumbers < getNumberOfWholeNumbers(y)) {
				largestNumWholeNumbers = getNumberOfWholeNumbers(y);
			}
			if (largestRelevantRightDecimal < getNumberOfDecimalPlaces(y)) {
				largestRelevantRightDecimal = getNumberOfDecimalPlaces(y);
			}
		}

		int totalSpace = largestNumWholeNumbers + 1 + largestRelevantRightDecimal;
		String formatString = "%" + totalSpace + "s";
		String returnString = "[";
		for (double x : vector) {
			String relevantPortion = getRelevantPortion(x, totalSpace);
			returnString += String.format(formatString, relevantPortion) + ", ";
		}
		returnString = returnString.substring(0, returnString.length() - 2);
		returnString += "]^t";
		return returnString;
	}
}