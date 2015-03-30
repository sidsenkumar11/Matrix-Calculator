import java.math.BigDecimal;

/**
 * A representation of a given Leslie Matrix
 * 
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Leslie {
	private Matrix a;
	private Vector population;

	/**
	 * Constructs the given Leslie matrix
	 * and the intitial population vector
	 * from the PDF specifications.
	 */
	public Leslie() {
		double[][] aMatrix = {{0, 1.2, 1.1, .9, .1, 0, 0, 0, 0},
							  {.7, 0, 0, 0, 0, 0, 0, 0, 0},
							  {0, .85, 0, 0, 0, 0, 0, 0, 0},
							  {0, 0, .9, 0, 0, 0, 0, 0, 0},
							  {0, 0, 0, .9, 0, 0, 0, 0, 0},
							  {0, 0, 0, 0, .88, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0, .8, 0, 0, 0},
							  {0, 0, 0, 0, 0, 0, .77, 0, 0},
							  {0, 0, 0, 0, 0, 0, 0, .4, 0}};
		this.a = new Matrix(aMatrix);

		double[] populationVector = {2.1, 1.9, 1.8, 2.1, 2.0, 1.7, 1.2, 0.9, 0.5};
		for (int i = 0; i < populationVector.length; i++) {
			populationVector[i] = populationVector[i] * 100000;
		}
		this.population = new Vector(populationVector);
	}

	/**
	 * Applies the power method
	 */
	public void passOneIteration() {
		this.population = MatrixCalculator.multiply(a, population);
	}

	public Matrix getLeslieMatrix() {
		return a;
	}

	public Vector getPopulationVector() {
		return population;
	}
}