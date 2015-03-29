import java.math.BigDecimal;

/**
 * Leslie Matrix Procedures using the MatrixCalculator classes
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
		double[] populationVector = {2.1, 1.9, 1.8, 2.1, 2.0, 1.7, 1.2, 0.9, 0.5};
		this.a = new Matrix(aMatrix);
		this.population = new Vector(populationVector);
		BigDecimal factor = new BigDecimal("" + 100000);
		for (int i = 0; i < population.numElements(); i++) {
			population.set(i, population.get(i).multiply(factor));
		}
	}
}