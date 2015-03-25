/**
 * Representation of a vector
 *
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Vector {
	
	private double[] vector;

	/**
	 * Constructs a matrix with the specified
	 * number of rows.
	 * @param rows The number of rows
	 */
	public Vector(int rows) {
		this.vector = new double[rows];
	}

	/**
	 * Constructs a matrix with the double array
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

	public String toString() {
		String returnString = "[";
		for (double x : vector) {
			returnString += x + ", ";
		}
		returnString = returnString.substring(0, returnString.length() - 2);
		returnString += "]";
		return returnString;
	}
}