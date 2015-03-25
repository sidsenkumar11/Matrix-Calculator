package hilbert;

import java.util.ArrayList;
import java.util.Arrays;
import Jama.Matrix;
import Jama.util.Maths;

/**
 * Hilbert Matrix Procedures
 * 
 * @author Ashika Ganesh
 * @version 1.0
 */
public class Hilbert {
	private double[][] array;
	private Matrix matrix, l, u, b, q, r;
	private int size;

	public Hilbert(int columns) {
		size = columns;
		b = new Matrix(columns, 1, 1);
		array = new double[columns][columns];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = (1.0 / (i + j + 1));
			}
		}
		matrix = new Matrix(array);
	}

	public Hilbert(double[][] array) {
		size = array.length;
		b = new Matrix(size, 1, 1);
		this.array = array;
		matrix = new Matrix(array);
	}

}